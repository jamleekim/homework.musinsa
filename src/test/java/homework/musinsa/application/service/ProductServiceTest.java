package homework.musinsa.application.service;

import static org.junit.jupiter.api.Assertions.*;


import homework.musinsa.application.port.out.ProductOutputPort;
import homework.musinsa.domain.enums.Category;
import homework.musinsa.domain.model.Product;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	private ProductOutputPort productOutputPort;

	@InjectMocks
	private ProductService service;

	@Nested
	class GetLowestPricesProductsTest {

		@Test
		void success_whenProductsExist() {
			// Arrange
			final Product p1 = new Product(1000,Category.TOP, "aaa", 1L, 10L);
			given(productOutputPort.getLowestPricesProducts()).willReturn(List.of(p1));

			// Act
			List<Product> result = service.getLowestPricesProducts();

			// Assert
			then(productOutputPort).should().getLowestPricesProducts();
			assertEquals(1, result.size());
			assertSame(p1, result.get(0));
		}

		@Test
		void failure_whenNoProducts() {
			// Arrange
			given(productOutputPort.getLowestPricesProducts()).willReturn(List.of());

			// Act & Assert
			IllegalStateException ex = assertThrows(
					IllegalStateException.class,
					() -> service.getLowestPricesProducts()
			);
			assertEquals("No products found", ex.getMessage());
			then(productOutputPort).should().getLowestPricesProducts();
		}
	}

	@Nested
	class GetLowestTotalProductsTest {

		@Test
		void success_alwaysDelegates() {
			// Arrange
			final Product p1 = new Product(200, Category.TOP, "bbb", 1L, 10L);
			final List<Product> expected = List.of(p1);
			given(productOutputPort.getLowestTotalProducts()).willReturn(expected);

			// Act
			List<Product> result = service.getLowestTotalProducts();

			// Assert
			then(productOutputPort).should().getLowestTotalProducts();
			assertSame(expected, result);
		}
	}

	@Nested
	class GetCategoryPriceRangeTest {

		@Test
		void success_whenValidCategory() {
			// Arrange
			final String displayName = Category.OUTER.getDisplayName(); // "아우터"
			final Product low = new Product(300, Category.ACCESSORY, "ccc", 1L, 10L);
			final Product high = new Product(5000, Category.OUTER, "ddd", 1L, 20L);
			final Pair<Product, Product> expected = Pair.of(low, high);
			given(productOutputPort.getCategoryPriceRange(Category.OUTER)).willReturn(expected);

			// Act
			Pair<Product, Product> result = service.getCategoryPriceRange(displayName);

			// Assert
			then(productOutputPort).should().getCategoryPriceRange(Category.OUTER);
			assertSame(expected, result);
		}

		@Test
		void failure_whenInvalidCategoryName() {
			// Arrange
			final String invalid = "알수없는카테고리";

			// Act & Assert
			assertThrows(
					IllegalArgumentException.class,
					() -> service.getCategoryPriceRange(invalid)
			);
			then(productOutputPort).should(never()).getCategoryPriceRange(any());
		}
	}
}