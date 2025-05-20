package homework.musinsa.infrastructure.adapter.out.persistance.repository;

import homework.musinsa.infrastructure.adapter.in.rest.exception.BrandNotFoundException;
import homework.musinsa.infrastructure.entity.BrandEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BrandRepositoryTest {

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private BrandRepository repository;

	@Test
	void viewBrand_whenPresent_returnsEntity() {
		// Arrange
		BrandEntity entity = new BrandEntity(1L, "TestBrand", false);
		given(repository.findById(1L)).willReturn(Optional.of(entity));

		// Act
		BrandEntity result = repository.viewBrand(1L);

		// Assert
		then(repository).should().findById(1L);
		assertThat(result, is(sameInstance(entity)));
	}

	@Test
	void viewBrand_whenAbsent_throwsBrandNotFoundException() {
		// Arrange
		given(repository.findById(42L)).willReturn(Optional.empty());

		// Act & Assert
		assertThrows(BrandNotFoundException.class, () -> repository.viewBrand(42L));
		then(repository).should().findById(42L);
	}
}