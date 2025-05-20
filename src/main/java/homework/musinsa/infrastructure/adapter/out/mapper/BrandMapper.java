package homework.musinsa.infrastructure.adapter.out.mapper;

import homework.musinsa.domain.model.Brand;
import homework.musinsa.infrastructure.entity.BrandEntity;

public class BrandMapper {

	public static Brand toDto(BrandEntity entity) {
		return new Brand(entity.getId(), entity.getName());
	}

}
