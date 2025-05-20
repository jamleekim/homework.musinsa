package homework.musinsa.infrastructure.adapter.in.rest;

import homework.musinsa.application.port.in.AddProductUseCase;
import homework.musinsa.application.port.in.DeleteProductUseCase;
import homework.musinsa.application.port.in.UpdateProductUseCase;
import homework.musinsa.infrastructure.adapter.in.rest.exception.ExceptionMessageResource;
import homework.musinsa.infrastructure.adapter.in.rest.request.AddProductCommand;
import homework.musinsa.infrastructure.adapter.in.rest.request.UpdateProductCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상품-어드민")
@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductAdminRestAdapter {

	private final AddProductUseCase addProductUseCase;
	private final UpdateProductUseCase updateProductUseCase;
	private final DeleteProductUseCase deleteProductUseCase;

	@Operation(
			summary = "상품 등록",
			description = "새로운 상품을 등록합니다."
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "201",
					description = "상품 등록 성공",
					content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
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
					description = "브랜드를 찾을 수 없음",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ExceptionMessageResource.class)
					)
			)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid AddProductCommand command) {
		addProductUseCase.create(command);
	}

	@Operation(
			summary = "상품 수정",
			description = "기존 상품의 정보를 수정합니다."
	)
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "상품 수정 성공"),
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
					description = "상품을 찾을 수 없음",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ExceptionMessageResource.class)
					)
			)
	})
	@PutMapping("/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long productId, @RequestBody @Valid UpdateProductCommand command) {
		updateProductUseCase.update(command.toCommand(productId));
	}

	@Operation(
			summary = "상품 삭제",
			description = "기존 상품을 삭제 처리합니다."
	)
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "상품 삭제 성공"),
			@ApiResponse(
					responseCode = "404",
					description = "상품을 찾을 수 없음",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ExceptionMessageResource.class)
					)
			)
	})
	@DeleteMapping("/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long productId) {
		deleteProductUseCase.delete(productId);
	}

}
