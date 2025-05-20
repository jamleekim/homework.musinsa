package homework.musinsa.infrastructure.adapter.in.rest;

import homework.musinsa.application.port.in.GetCategoryPriceRangeUseCase;
import homework.musinsa.application.port.in.GetLowestPricesProductsUseCase;
import homework.musinsa.infrastructure.adapter.in.rest.exception.ExceptionMessageResource;
import homework.musinsa.infrastructure.adapter.in.rest.response.CategoryPriceRangeResponse;
import homework.musinsa.infrastructure.adapter.in.rest.response.LowestPricesProductsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "카테고리")
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Validated
public class CategoryRestAdapter {

	private final GetLowestPricesProductsUseCase getLowestPricesProductsUseCase;

	private final GetCategoryPriceRangeUseCase getCategoryPriceRangeUseCase;

	@Operation(
			summary = "카테고리별 최저가 상품 조회",
			description = "각 카테고리별로 가격이 가장 낮은 상품과 해당 상품의 브랜드 정보를 조회합니다."
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "조회 성공",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = LowestPricesProductsResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "500",
					description = "내부 서버 오류",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ExceptionMessageResource.class)
					)
			)
	})
	@GetMapping("/lowest-prices")
	public ResponseEntity<LowestPricesProductsResponse> getLowestPricesProducts() {

		final var products = getLowestPricesProductsUseCase.getLowestPricesProducts();
		final var result = LowestPricesProductsResponse.from(products);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@Operation(
			summary = "카테고리 가격 범위 조회",
			description = "{categoryName} 카테고리의 최저가 및 최고가 상품 정보를 조회합니다."
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "조회 성공",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = CategoryPriceRangeResponse.class)
					)
			),
			@ApiResponse(
					responseCode = "400",
					description = "잘못된 요청 데이터",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ExceptionMessageResource.class)
					)
			),
			@ApiResponse(
					responseCode = "404",
					description = "해당 카테고리를 찾을 수 없음",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ExceptionMessageResource.class)
					)
			),
			@ApiResponse(
					responseCode = "500",
					description = "내부 서버 오류",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ExceptionMessageResource.class)
					)
			)
	})
	@GetMapping("/{categoryName}/price-range")
	public ResponseEntity<CategoryPriceRangeResponse> getCategoryPriceRange(
			@Size(min = 2, max = 50, message = "카테고리 이름은 2자 이상 50자 이하이어야 합니다.")
			@PathVariable String categoryName) {

		final var pair = getCategoryPriceRangeUseCase.getCategoryPriceRange(categoryName);
		final var result = CategoryPriceRangeResponse.from(pair);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
