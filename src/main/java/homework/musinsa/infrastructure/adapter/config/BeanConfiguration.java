package homework.musinsa.infrastructure.adapter.config;

import homework.musinsa.application.service.BrandAdminService;
import homework.musinsa.application.service.ProductAdminService;
import homework.musinsa.application.service.ProductService;
import homework.musinsa.infrastructure.adapter.out.persistance.BrandPersistenceAdapter;
import homework.musinsa.infrastructure.adapter.out.persistance.ProductPersistenceAdapter;
import homework.musinsa.infrastructure.adapter.out.persistance.repository.BrandRepository;
import homework.musinsa.infrastructure.adapter.out.persistance.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	@Bean
	public ProductPersistenceAdapter productPersistenceAdapter(final ProductRepository productRepository, final BrandRepository brandRepository) {
		return new ProductPersistenceAdapter(productRepository, brandRepository);
	}

	@Bean
	public ProductService productService(final ProductPersistenceAdapter productPersistenceAdapter) {
		return new ProductService(productPersistenceAdapter);
	}

	@Bean
	public BrandPersistenceAdapter brandPersistenceAdapter(final BrandRepository brandRepository) {
		return new BrandPersistenceAdapter(brandRepository);
	}

	@Bean
	public BrandAdminService brandAdminService(final BrandPersistenceAdapter brandPersistenceAdapter) {
		return new BrandAdminService(brandPersistenceAdapter);
	}

	@Bean
	public ProductAdminService productAdminService(final ProductPersistenceAdapter productPersistenceAdapter) {
		return new ProductAdminService(productPersistenceAdapter);
	}

}
