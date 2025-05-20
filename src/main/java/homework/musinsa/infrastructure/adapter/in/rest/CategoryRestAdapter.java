package homework.musinsa.infrastructure.adapter.in.rest;

import homework.musinsa.application.port.in.GetCategoryPriceRangeUseCase;
import homework.musinsa.application.port.in.GetLowestPricesProductsUseCase;
import homework.musinsa.infrastructure.adapter.in.rest.response.CategoryPriceRangeResponse;
import homework.musinsa.infrastructure.adapter.in.rest.response.LowestPricesProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryRestAdapter {

	private final GetLowestPricesProductsUseCase getLowestPricesProductsUseCase;

	private final GetCategoryPriceRangeUseCase getCategoryPriceRangeUseCase;

	@GetMapping("/lowest-prices")
	public ResponseEntity<LowestPricesProductsResponse> getLowestPricesProducts() {

		final var products = getLowestPricesProductsUseCase.getLowestPricesProducts();
		final var result = LowestPricesProductsResponse.from(products);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@GetMapping("/{categoryName}/price-range")
	public ResponseEntity<CategoryPriceRangeResponse> getCategoryPriceRange(@PathVariable String categoryName) {

		final var pair = getCategoryPriceRangeUseCase.getCategoryPriceRange(categoryName);
		final var result = CategoryPriceRangeResponse.from(pair);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
