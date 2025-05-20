package homework.musinsa.infrastructure.adapter.out.persistance.repository;

import homework.musinsa.infrastructure.entity.BrandEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import static homework.musinsa.infrastructure.adapter.in.rest.exception.ExceptionMessageBuilder.EntityNotFoundException.message;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

	default BrandEntity viewBrand(final Long id) {
		return findById(id)
				.orElseThrow(() -> new EntityNotFoundException(message(BrandEntity.class, "brandId", id)));
	}

}
