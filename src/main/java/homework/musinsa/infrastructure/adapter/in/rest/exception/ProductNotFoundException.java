package homework.musinsa.infrastructure.adapter.in.rest.exception;

import homework.musinsa.infrastructure.entity.ProductEntity;

public class ProductNotFoundException extends ResourceNotFoundException {

	public ProductNotFoundException(Object brandIdValue) {
		super(ProductEntity.class.getSimpleName(), "ID", brandIdValue);
	}

	public ProductNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(resourceName, fieldName, fieldValue);
	}

}
