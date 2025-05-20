package homework.musinsa.infrastructure.adapter.out.persistance.repository;

import homework.musinsa.infrastructure.adapter.in.rest.exception.BrandNotFoundException;
import homework.musinsa.infrastructure.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

	default BrandEntity viewBrand(final Long brandId) {
		return findById(brandId)
				.orElseThrow(() -> new BrandNotFoundException(brandId));
	}

}
