package homework.musinsa.infrastructure.adapter.out.persistance;

import homework.musinsa.application.port.out.BrandOutputPort;
import homework.musinsa.domain.model.Brand;
import homework.musinsa.infrastructure.adapter.out.mapper.BrandMapper;
import homework.musinsa.infrastructure.adapter.out.persistance.repository.BrandRepository;
import homework.musinsa.infrastructure.entity.BrandEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class BrandPersistenceAdapter implements BrandOutputPort {

	private final BrandRepository brandRepository;

	@Override
	@Transactional(readOnly = true)
	public Brand getBrand(long id) {
		final var brandEntity = brandRepository.viewBrand(id);
		return BrandMapper.toDto(brandEntity);
	}

	@Override
	@Transactional
	public Brand addBrand(String name) {
		final var inserted = brandRepository.save(new BrandEntity(name));
		return BrandMapper.toDto(inserted);
	}

	@Override
	@Transactional
	public void updateBrand(Brand brand) {
		final var brandEntity = brandRepository.viewBrand(brand.id());
		brandEntity.setName(brand.name());
	}

	@Override
	@Transactional
	public void deleteBrand(long id) {
		final var brandEntity = brandRepository.viewBrand(id);
		brandEntity.disable();
	}

}
