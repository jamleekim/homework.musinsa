package homework.musinsa.infrastructure.adapter.out.persistance.repository;

import homework.musinsa.infrastructure.adapter.in.rest.exception.BrandNotFoundException;
import homework.musinsa.domain.enums.Category;
import homework.musinsa.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryCustom {

    Optional<ProductEntity> findTopByCategoryOrderByPriceAsc(Category category);

    Optional<ProductEntity> findTopByCategoryOrderByPriceDesc(Category category);

    default ProductEntity viewProduct(final Long productId) {
        return findById(productId)
                .orElseThrow(() -> new BrandNotFoundException(ProductEntity.class.getName(), "productId", productId));
    }

}