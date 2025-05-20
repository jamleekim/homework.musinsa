package homework.musinsa.infrastructure.adapter.in.rest;


import homework.musinsa.application.port.in.AddBrandUseCase;
import homework.musinsa.application.port.in.DeleteBrandUseCase;
import homework.musinsa.application.port.in.UpdateBrandUseCase;
import homework.musinsa.infrastructure.adapter.in.rest.exception.ExceptionMessageResource;
import homework.musinsa.infrastructure.adapter.in.rest.request.AddBrandCommand;
import homework.musinsa.infrastructure.adapter.in.rest.request.DeleteBrandCommand;
import homework.musinsa.infrastructure.adapter.in.rest.request.UpdateBrandCommand;
import homework.musinsa.infrastructure.adapter.in.rest.response.CreatedBrandResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "브랜드-어드민")
@RestController
@RequestMapping("/api/admin/brands")
@RequiredArgsConstructor
public class BrandAdminRestAdapter {

	private final AddBrandUseCase addBrandUseCase;
	private final UpdateBrandUseCase updateBrandUseCase;
	private final DeleteBrandUseCase deleteBrandUseCase;

	@Operation(
			summary = "브랜드 등록",
			description = "새로운 브랜드를 등록합니다."
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "201",
					description = "브랜드 등록 성공",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = CreatedBrandResponse.class)
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
					responseCode = "409",
					description = "이미 존재하는 브랜드",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ExceptionMessageResource.class)
					)
			)
	})
	@PostMapping
	public ResponseEntity<CreatedBrandResponse> create(@RequestBody @Valid AddBrandCommand name) {
		final var brand = addBrandUseCase.addBrand(name);
		final var result = CreatedBrandResponse.from(brand);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@Operation(
			summary = "브랜드 수정",
			description = "기존 브랜드의 이름을 수정합니다."
	)
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "브랜드 수정 성공"),
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
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody @Valid UpdateBrandCommand update, @PathVariable Long id) {
		updateBrandUseCase.updateBrand(update.toCommand(id));
	}

	@Operation(
			summary = "브랜드 삭제",
			description = "기존 브랜드를 삭제 처리합니다."
	)
	@ApiResponses({
			@ApiResponse(responseCode = "204", description = "브랜드 삭제 성공"),
			@ApiResponse(
					responseCode = "404",
					description = "브랜드를 찾을 수 없음",
					content = @Content(
							mediaType = MediaType.APPLICATION_JSON_VALUE,
							schema = @Schema(implementation = ExceptionMessageResource.class)
					)
			)
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		deleteBrandUseCase.deleteBrand(new DeleteBrandCommand(id));
	}

}
