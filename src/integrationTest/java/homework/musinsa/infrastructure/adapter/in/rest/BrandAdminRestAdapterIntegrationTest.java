package homework.musinsa.infrastructure.adapter.in.rest;

import com.jayway.jsonpath.JsonPath;
import homework.musinsa.infrastructure.adapter.out.persistance.repository.BrandRepository;
import homework.musinsa.infrastructure.entity.BrandEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class BrandAdminRestAdapterIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BrandRepository brandRepository;

	@BeforeEach
	void setUp() {
		brandRepository.deleteAll();
	}

	@Test
	void createBrand_returnsCreatedResponse_andPersists() throws Exception {
		// Act
		mockMvc.perform(post("/api/admin/brands")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"NewBrand\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	void updateBrand_returnsNoContent_andUpdatesName() throws Exception {
		// Arrange
		BrandEntity saved = brandRepository.save(new BrandEntity("OldName"));

		// Act
		mockMvc.perform(put("/api/admin/brands/{id}", saved.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"UpdatedName\"}"))
				.andExpect(status().isNoContent());

		// Assert
		BrandEntity updated = brandRepository.findById(saved.getId()).orElseThrow();
		assertThat(updated.getName()).isEqualTo("UpdatedName");
	}

	@Test
	void deleteBrand_returnsNoContent_andMarksDeleted() throws Exception {
		// Arrange
		BrandEntity saved = brandRepository.save(new BrandEntity("ToDelete"));

		// Act
		mockMvc.perform(delete("/api/admin/brands/{id}", saved.getId()))
				.andExpect(status().isNoContent());

		// Assert
		BrandEntity deleted = brandRepository.findById(saved.getId()).orElseThrow();
		assertThat(deleted.isDeleted()).isTrue();
	}
}