package homework.musinsa.infrastructure.adapter.in.rest;


import homework.musinsa.domain.enums.Category;
import homework.musinsa.infrastructure.adapter.out.persistance.repository.BrandRepository;
import homework.musinsa.infrastructure.adapter.out.persistance.repository.ProductRepository;
import homework.musinsa.infrastructure.entity.BrandEntity;
import homework.musinsa.infrastructure.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class CategoryRestAdapterIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private ProductRepository productRepository;

	@BeforeEach
	void setUp() {
		// 깨끗한 상태
		productRepository.deleteAll();
		brandRepository.deleteAll();

		// 브랜드 A, B 생성
		BrandEntity brandA = brandRepository.save(new BrandEntity(null, "BrandA", false));
		BrandEntity brandB = brandRepository.save(new BrandEntity(null, "BrandB", false));

		// TOP 카테고리: A(100), B(200)
		productRepository.save(new ProductEntity(null, 100, Category.TOP,   brandA, false));
		productRepository.save(new ProductEntity(null, 200, Category.TOP,   brandB, false));
		// OUTER 카테고리: A(300), B(150)
		productRepository.save(new ProductEntity(null, 300, Category.OUTER, brandA, false));
		productRepository.save(new ProductEntity(null, 150, Category.OUTER, brandB, false));
	}

	@Test
	void getLowestPricesProducts_returnsOkAndCorrectData() throws Exception {
		mockMvc.perform(get("/api/categories/lowest-prices")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.totalPrice").value(250))
				.andExpect(jsonPath("$.products", hasSize(2)))
				// 0번(첫 번째) 요소: '상의'
				.andExpect(jsonPath("$.products[0].category").value("상의"))
				.andExpect(jsonPath("$.products[0].price").value(100))
				.andExpect(jsonPath("$.products[0].brandName").value("BrandA"))

				// 1번(두 번째) 요소: '아우터'
				.andExpect(jsonPath("$.products[1].category").value("아우터"))
				.andExpect(jsonPath("$.products[1].price").value(150))
				.andExpect(jsonPath("$.products[1].brandName").value("BrandB"));
	}

	@Test
	void getCategoryPriceRange_returnsOkAndCorrectData() throws Exception {
		mockMvc.perform(get("/api/categories/{categoryName}/price-range","상의")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.카테고리").value("상의"))
				.andExpect(jsonPath("$.최저가[0].브랜드").value("BrandA"))
				.andExpect(jsonPath("$.최저가[0].가격").value(100))
				.andExpect(jsonPath("$.최고가[0].브랜드").value("BrandB"))
				.andExpect(jsonPath("$.최고가[0].가격").value(200));
	}
}