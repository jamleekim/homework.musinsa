package homework.musinsa.application.service;

import homework.musinsa.application.port.out.BrandOutputPort;
import homework.musinsa.domain.model.Brand;
import homework.musinsa.infrastructure.adapter.in.rest.request.AddBrandCommand;
import homework.musinsa.infrastructure.adapter.in.rest.request.DeleteBrandCommand;
import homework.musinsa.infrastructure.adapter.in.rest.request.UpdateBrandCommand;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class BrandAdminServiceTest {

	@Mock
	private BrandOutputPort brandOutputPort;

	@InjectMocks
	private BrandAdminService service;

	@Nested
	class AddBrandTest {

		@Test
		void should_delegate_to_outputPort_and_return_brand() {
			// Arrange
			Brand expected = new Brand(1L, "NewBrand");
			given(brandOutputPort.addBrand("NewBrand")).willReturn(expected);

			// Act
			Brand actual = service.addBrand(new AddBrandCommand("NewBrand"));

			// Assert
			then(brandOutputPort).should().addBrand("NewBrand");
			assertThat(actual, is(equalTo(expected)));
		}
	}

	@Nested
	class UpdateBrandTest {

		@Test
		void should_call_get_and_update_when_brand_exists() {
			// Arrange
			long id = 1L;
			String newName = "UpdatedBrand";
			UpdateBrandCommand cmd = new UpdateBrandCommand(id, newName);
			Brand existing = new Brand(id, "OldName");
			given(brandOutputPort.getBrand(id)).willReturn(existing);

			// Act
			service.updateBrand(cmd);

			// Assert
			InOrder inOrder = inOrder(brandOutputPort);
			inOrder.verify(brandOutputPort).getBrand(id);
			inOrder.verify(brandOutputPort).updateBrand(new Brand(id, newName));
		}

		@Test
		void should_throw_when_brand_not_found() {
			// Arrange
			long id = 99L;
			UpdateBrandCommand cmd = new UpdateBrandCommand(id, "X");
			given(brandOutputPort.getBrand(id))
					.willThrow(new NoSuchElementException("Not found"));

			// Act / then
			assertThrows(NoSuchElementException.class, () -> service.updateBrand(cmd));
			then(brandOutputPort).should().getBrand(id);
			then(brandOutputPort).should(never()).updateBrand(any());
		}
	}

	@Nested
	class DeleteBrandTest {

		@Test
		void should_call_get_and_delete_when_brand_exists() {
			// Arrange
			long id = 2L;
			DeleteBrandCommand cmd = new DeleteBrandCommand(id);
			Brand existing = new Brand(id, "Name");
			given(brandOutputPort.getBrand(id)).willReturn(existing);

			// Act
			service.deleteBrand(cmd);

			// Assert
			InOrder inOrder = inOrder(brandOutputPort);
			inOrder.verify(brandOutputPort).getBrand(id);
			inOrder.verify(brandOutputPort).deleteBrand(id);
		}

		@Test
		void should_throw_when_brand_not_found() {
			// Arrange
			long id = 100L;
			DeleteBrandCommand cmd = new DeleteBrandCommand(id);
			given(brandOutputPort.getBrand(id))
					.willThrow(new NoSuchElementException("Not found"));

			// Act & Assert
			assertThrows(NoSuchElementException.class, () -> service.deleteBrand(cmd));
			then(brandOutputPort).should().getBrand(id);
			then(brandOutputPort).should(never()).deleteBrand(anyLong());
		}
	}

}