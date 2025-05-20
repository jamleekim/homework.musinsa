package homework.musinsa.infrastructure.adapter.out.persistance.repository;

import homework.musinsa.domain.enums.Category;
import homework.musinsa.infrastructure.entity.ProductEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static homework.musinsa.infrastructure.adapter.in.rest.exception.ExceptionMessageBuilder.EntityNotFoundException.message;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryCustom {

    Optional<ProductEntity> findTopByCategoryOrderByPriceAsc(Category category);

    Optional<ProductEntity> findTopByCategoryOrderByPriceDesc(Category category);

    default ProductEntity viewProduct(final Long productId) {
        return findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(message(ProductEntity.class, "productId", productId)));
    }

}