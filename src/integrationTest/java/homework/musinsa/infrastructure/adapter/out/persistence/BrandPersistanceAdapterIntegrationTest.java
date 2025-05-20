package homework.musinsa.infrastructure.adapter.out.persistence;

import homework.musinsa.domain.model.Brand;
import homework.musinsa.infrastructure.adapter.in.rest.exception.BrandNotFoundException;
import homework.musinsa.infrastructure.adapter.out.persistance.BrandPersistenceAdapter;
import homework.musinsa.infrastructure.adapter.out.persistance.repository.BrandRepository;
import homework.musinsa.infrastructure.entity.BrandEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
public class BrandPersistanceAdapterIntegrationTest {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private BrandPersistenceAdapter adapter;

	@BeforeEach
	void setUp() {
		brandRepository.deleteAll();
	}

	@Test
	void addBrand_persistsAndReturnsDto() {
		// Act
		Brand dto = adapter.addBrand("NewBrand");

		// Assert
		assertThat(dto).isNotNull();
		assertThat(dto.id()).isNotNull();
		assertThat(dto.name()).isEqualTo("NewBrand");

		BrandEntity saved = brandRepository.findById(dto.id()).orElseThrow();
		assertThat(saved.getName()).isEqualTo("NewBrand");
		assertThat(saved.isDeleted()).isFalse();
	}

	@Test
	void getBrand_existingId_returnsDto() {
		// Arrange
		BrandEntity saved = brandRepository.save(new BrandEntity("ExistingBrand"));

		// Act
		Brand dto = adapter.getBrand(saved.getId());

		// Assert
		assertThat(dto.id()).isEqualTo(saved.getId());
		assertThat(dto.name()).isEqualTo("ExistingBrand");
	}

	@Test
	void getBrand_nonExistingId_throwsNotFound() {
		// Act / Assert
		assertThatThrownBy(() -> adapter.getBrand(999L))
				.isInstanceOf(BrandNotFoundException.class);
	}

	@Test
	void updateBrand_changesName() {
		// Arrange
		BrandEntity saved = brandRepository.save(new BrandEntity("OldName"));
		Brand update = new Brand(saved.getId(), "UpdatedName");

		// Act
		adapter.updateBrand(update);

		// Assert
		BrandEntity reloaded = brandRepository.findById(saved.getId()).orElseThrow();
		assertThat(reloaded.getName()).isEqualTo("UpdatedName");
	}

	@Test
	void deleteBrand_marksEntityDeleted() {
		// Arrange
		BrandEntity saved = brandRepository.save(new BrandEntity("ToDelete"));

		// Act
		adapter.deleteBrand(saved.getId());

		// Assert
		BrandEntity deleted = brandRepository.findById(saved.getId()).orElseThrow();
		assertThat(deleted.isDeleted()).isTrue();
	}


}
