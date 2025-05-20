package homework.musinsa.infrastructure.adapter.in.rest;

import homework.musinsa.application.port.in.AddProductUseCase;
import homework.musinsa.application.port.in.DeleteProductUseCase;
import homework.musinsa.application.port.in.UpdateProductUseCase;
import homework.musinsa.infrastructure.adapter.in.rest.request.AddProductCommand;
import homework.musinsa.infrastructure.adapter.in.rest.request.UpdateProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductAdminRestAdapter {

	private final AddProductUseCase addProductUseCase;
	private final UpdateProductUseCase updateProductUseCase;
	private final DeleteProductUseCase deleteProductUseCase;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody AddProductCommand command) {
		addProductUseCase.create(command);
	}

	@PutMapping("/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long productId, @RequestBody UpdateProductCommand command) {
		updateProductUseCase.update(command.toCommand(productId));
	}

	@DeleteMapping("/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long productId) {
		deleteProductUseCase.delete(productId);
	}

}
