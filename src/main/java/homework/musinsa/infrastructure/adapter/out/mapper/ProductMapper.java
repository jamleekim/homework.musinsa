package homework.musinsa.infrastructure.adapter.out.mapper;

import homework.musinsa.domain.model.Product;
import homework.musinsa.infrastructure.entity.ProductEntity;

public class ProductMapper {

	public static Product toDto(ProductEntity entity) {
		final var brand = entity.getBrand();
		return new Product(entity.getPrice(), entity.getCategory(), brand.getName(), entity.getId(), entity.getId());
	}

}
