package homework.musinsa.infrastructure.adapter.out.persistance.repository;

import homework.musinsa.infrastructure.entity.ProductEntity;

import java.util.List;

public interface ProductRepositoryCustom {

	List<ProductEntity> findLowestPricesProducts();

	List<ProductEntity> findLowestTotalProducts();

}
