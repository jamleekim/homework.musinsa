package homework.musinsa.infrastructure.adapter.out.persistance.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import homework.musinsa.infrastructure.entity.ProductEntity;
import homework.musinsa.infrastructure.entity.QProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	private static final QProductEntity PRODUCT = QProductEntity.productEntity;
	private static final QProductEntity PRODUCT_SUB = new QProductEntity("PRODUCT_SUB");

	@Override
	public List<ProductEntity> findLowestPricesProducts() {

		final var productEntities = jpaQueryFactory.selectFrom(PRODUCT)
				.where(PRODUCT.price.eq(
						JPAExpressions
								.select(PRODUCT_SUB.price.min())
								.from(PRODUCT_SUB)
								.where(PRODUCT_SUB.category.eq(PRODUCT.category))
				))
				.fetch();

		return productEntities.stream()
				.collect(Collectors.toMap(
						ProductEntity::getCategory,   // key: 카테고리
						Function.identity(),          // value: 첫 번째 상품만 유지
						(existing, replacement) -> existing
				))
				.values()
				.stream()
				.sorted(Comparator.comparing(ProductEntity::getCategory))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductEntity> findLowestTotalProducts() {

		final var cheapestBrandId = jpaQueryFactory.select(PRODUCT.brand.id)
				.from(PRODUCT)
				.groupBy(PRODUCT.brand.id)
				.orderBy(PRODUCT.price.sum().asc())
				.limit(1)
				.fetchOne();

		return jpaQueryFactory.selectFrom(PRODUCT)
				.where(PRODUCT.brand.id.eq(cheapestBrandId))
				.fetch();
	}
}
