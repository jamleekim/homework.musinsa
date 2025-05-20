package homework.musinsa.infrastructure.adapter.in.rest.exception;

import homework.musinsa.infrastructure.entity.BrandEntity;

public class BrandNotFoundException extends ResourceNotFoundException {

	public BrandNotFoundException(Object brandIdValue) {
		super(BrandEntity.class.getSimpleName(), "ID", brandIdValue);
	}

	public BrandNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(resourceName, fieldName, fieldValue);
	}

}
