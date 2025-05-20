package homework.musinsa.infrastructure.adapter.in.rest;

import homework.musinsa.application.port.in.GetLowestTotalUseCase;
import homework.musinsa.infrastructure.adapter.in.rest.response.LowestTotalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandRestAdapter {

	private final GetLowestTotalUseCase getLowestTotalUseCase;

	@GetMapping("/lowest-total")
	public ResponseEntity<LowestTotalResponse> getLowestTotal() {

		final var products = getLowestTotalUseCase.getLowestTotalProducts();
		final var result = LowestTotalResponse.from(products);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
