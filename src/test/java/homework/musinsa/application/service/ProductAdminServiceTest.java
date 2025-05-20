package homework.musinsa.application.service;

import homework.musinsa.application.port.out.ProductOutputPort;
import homework.musinsa.domain.enums.Category;
import homework.musinsa.domain.model.Product;
import homework.musinsa.infrastructure.adapter.in.rest.request.AddProductCommand;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductAdminServiceTest {

	@Mock
	private ProductOutputPort productOutputPort;

	@InjectMocks
	private ProductAdminService service;

	@Nested
	class CreateTest {

		@Test
		void should_build_product_and_delegate_to_outputPort_and_return_created() {

			// Arrange
			final var command = new AddProductCommand(10000 ,Category.TOP, 1L);
			final var product = Product.builder()
					.price(command.price())
					.brandId(command.brandId())
					.category(command.category())
					.build();
			final var expected = new Product(10000, Category.TOP, "aaa", 1L, 10L);
			when(productOutputPort.create(product)).thenReturn(expected);

			// Act
			Product actual = service.create(command);

			// Assert
			assertThat(actual, is(equalTo(expected)));
		}
	}

}