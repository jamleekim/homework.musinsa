package homework.musinsa.infrastructure.adapter.out.mapper;

import homework.musinsa.domain.enums.Category;
import homework.musinsa.domain.model.Product;
import homework.musinsa.infrastructure.entity.BrandEntity;
import homework.musinsa.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

	@Test
	void toDto_mapsAllFieldsCorrectly() {
		// Arrange
		// BrandEntity: id=5, name="TestBrand", isDeleted=false
		BrandEntity brand = new BrandEntity(5L, "TestBrand", false);
		// ProductEntity: id=7, price=1500, category=BAG, brand=brand, isDeleted=false
		ProductEntity entity = new ProductEntity(7L, 1500, Category.BAG, brand, false);

		// Act
		Product dto = ProductMapper.toDto(entity);

		// Assert
		assertThat(dto).isNotNull();
		assertThat(dto.price()).isEqualTo(1500);
		assertThat(dto.category()).isEqualTo(Category.BAG);
		assertThat(dto.brandName()).isEqualTo("TestBrand");
		assertThat(dto.productId()).isEqualTo(7L);
		assertThat(dto.brandId()).isEqualTo(7L);
	}

	@Test
	void toDto_throwsWhenBrandIsNull() {
		// Arrange
		ProductEntity entity = new ProductEntity(8L, 2000, Category.HAT, null, false);

		// Act & Assert
		assertThatThrownBy(() -> ProductMapper.toDto(entity))
				.isInstanceOf(NullPointerException.class);
	}
}