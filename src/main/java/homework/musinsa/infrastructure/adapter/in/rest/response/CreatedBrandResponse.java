package homework.musinsa.infrastructure.adapter.in.rest.response;

import homework.musinsa.domain.model.Brand;

public record CreatedBrandResponse(long id, String name) {

	public static CreatedBrandResponse from(Brand brand) {
		return new CreatedBrandResponse(brand.id(), brand.name());
	}

}
