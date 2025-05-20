package homework.musinsa.infrastructure.adapter.in.rest;


import homework.musinsa.application.port.in.AddBrandUseCase;
import homework.musinsa.application.port.in.DeleteBrandUseCase;
import homework.musinsa.application.port.in.UpdateBrandUseCase;
import homework.musinsa.infrastructure.adapter.in.rest.request.DeleteBrandCommand;
import homework.musinsa.infrastructure.adapter.in.rest.request.UpdateBrandCommand;
import homework.musinsa.infrastructure.adapter.in.rest.response.CreatedBrandResponse;
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
@RequestMapping("/api/admin/brands")
@RequiredArgsConstructor
public class BrandAdminRestAdapter {

	private final AddBrandUseCase addBrandUseCase;
	private final UpdateBrandUseCase updateBrandUseCase;
	private final DeleteBrandUseCase deleteBrandUseCase;

	@PostMapping
	public ResponseEntity<CreatedBrandResponse> create(@RequestBody String name) {
		final var brand = addBrandUseCase.addBrand(name);
		final var result = CreatedBrandResponse.from(brand);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody UpdateBrandCommand update, @PathVariable Long id) {
		updateBrandUseCase.updateBrand(update.toCommand(id));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		deleteBrandUseCase.deleteBrand(new DeleteBrandCommand(id));
	}

}
