package homework.musinsa.application.service;

import homework.musinsa.application.port.in.AddProductUseCase;
import homework.musinsa.application.port.in.DeleteProductUseCase;
import homework.musinsa.application.port.in.UpdateProductUseCase;
import homework.musinsa.application.port.out.ProductOutputPort;
import homework.musinsa.domain.model.Product;
import homework.musinsa.infrastructure.adapter.in.rest.request.AddProductCommand;
import homework.musinsa.infrastructure.adapter.in.rest.request.UpdateProductCommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductAdminService implements AddProductUseCase, UpdateProductUseCase, DeleteProductUseCase {

	private final ProductOutputPort productOutputPort;

	@Override
	public Product create(AddProductCommand command) {
		final var product = Product.builder()
				.price(command.price())
				.brandId(command.brandId())
				.category(command.category())
				.build();
		return productOutputPort.create(product);
	}

	@Override
	public void update(UpdateProductCommand command) {
		final var product = Product.builder()
				.price(command.price())
				.brandId(command.brandId())
				.category(command.category())
				.build();
		productOutputPort.update(product);
	}

	@Override
	public void delete(Long productId) {
		productOutputPort.delete(productId);
	}


}
