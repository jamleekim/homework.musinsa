package homework.musinsa.infrastructure.adapter.out.persistance;

import homework.musinsa.application.port.out.ProductOutputPort;
import homework.musinsa.domain.enums.Category;
import homework.musinsa.domain.model.Product;
import homework.musinsa.infrastructure.adapter.out.mapper.ProductMapper;
import homework.musinsa.infrastructure.adapter.out.persistance.repository.BrandRepository;
import homework.musinsa.infrastructure.adapter.out.persistance.repository.ProductRepository;
import homework.musinsa.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductOutputPort {

	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> getLowestPricesProducts() {

		final var productEntities = productRepository.findLowestPricesProducts();

		return productEntities.stream()
				.map(ProductMapper::toDto)
				.sorted(Comparator.comparingInt(p -> p.category().getRank()))
				.toList();

	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getLowestTotalProducts() {

		final var productEntities = productRepository.findLowestTotalProducts();

		return productEntities.stream()
				.map(ProductMapper::toDto)
				.sorted(Comparator.comparingInt(p -> p.category().getRank()))
				.toList();

	}

	@Override
	@Transactional(readOnly = true)
	public Pair<Product, Product> getCategoryPriceRange(Category category) {

		final var minPriceProduct = productRepository.findTopByCategoryOrderByPriceAsc(category)
				.map(ProductMapper::toDto)
				.orElse(null);
		final var maxPriceProduct = productRepository.findTopByCategoryOrderByPriceDesc(category)
				.map(ProductMapper::toDto)
				.orElse(null);

		return Pair.of(minPriceProduct, maxPriceProduct);
	}

	@Override
	@Transactional(readOnly = true)
	public Product getProduct(Long productId) {
		final var productEntity = productRepository.viewProduct(productId);
		return ProductMapper.toDto(productEntity);
	}

	@Override
	@Transactional
	public Product create(Product product) {
		final var brandEntity = brandRepository.viewBrand(product.brandId());
		final var insert = new ProductEntity(product.price(), product.category(), brandEntity);
		productRepository.save(insert);
		return ProductMapper.toDto(insert);
	}

	@Override
	@Transactional
	public void update(Product product) {
		final var productEntity = productRepository.viewProduct(product.productId());
		productEntity.setPrice( product.price());
		productEntity.setCategory(product.category());
	}

	@Override
	@Transactional
	public void delete(Long productId) {
		final var productEntity = productRepository.viewProduct(productId);
		productEntity.disable();
	}

}