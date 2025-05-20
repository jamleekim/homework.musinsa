package homework.musinsa.infrastructure.adapter.in.rest;

import homework.musinsa.application.port.in.GetLowestTotalUseCase;
import homework.musinsa.infrastructure.adapter.in.rest.exception.ExceptionMessageResource;
import homework.musinsa.infrastructure.adapter.in.rest.response.LowestTotalResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "브랜드")
@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandRestAdapter {

	private final GetLowestTotalUseCase getLowestTotalUseCase;

	@Operation(
			summary = "전체 합계 최저 브랜드 조회",
			description = "8개 카테고리 상품을 한 브랜드에서 모두 구매할 때, 총 합계가 가장 낮은 브랜드와 해당 브랜드의 상품별 가격 정보를 조회합니다."
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "조회 성공",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = LowestTotalResponse.class)
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
	@GetMapping("/lowest-total")
	public ResponseEntity<LowestTotalResponse> getLowestTotal() {

		final var products = getLowestTotalUseCase.getLowestTotalProducts();
		final var result = LowestTotalResponse.from(products);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
