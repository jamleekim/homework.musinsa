package homework.musinsa.infrastructure.adapter.in.rest.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionMessageResource> handleHttpMessageNotReadable(
			final HttpMessageNotReadableException ex) {
		Throwable cause = ex.getCause();
		if (cause instanceof IllegalArgumentException) {
			final var body = new ExceptionMessageResource(cause.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		}
		// 다른 원인이 있는 경우
		final var body = new ExceptionMessageResource("Malformed JSON request.");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionMessageResource> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {

		BindingResult bindingResult = e.getBindingResult();
		String errorMessage = "검증 오류가 발생했습니다."; // 기본 에러 메시지

		if (bindingResult.hasFieldErrors()) {
			FieldError fieldError = bindingResult.getFieldErrors().get(0); // 첫 번째 필드 에러
			errorMessage = fieldError.getDefaultMessage(); // 예: "가격은 1원 이상이어야 합니다."
		} else if (bindingResult.hasGlobalErrors()) {
			// 필드 에러가 아닌 글로벌 에러가 있다면 첫 번째 글로벌 에러 메시지를 사용
			errorMessage = bindingResult.getGlobalErrors().get(0).getDefaultMessage();
		}

		final var body = new ExceptionMessageResource(errorMessage);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ExceptionMessageResource> handleConstraintViolationException(
			final ConstraintViolationException e) {
		String extractedMessage = "입력 값 검증 오류가 발생했습니다."; // 기본 메시지

		if (!e.getConstraintViolations().isEmpty()) {
			ConstraintViolation<?> violation = e.getConstraintViolations().iterator().next();
			String fullMessage = violation.getMessage(); // 예: "getCategoryPriceRange.categoryName: 실제 메시지"
			String propertyPathString = violation.getPropertyPath().toString(); // 예: "getCategoryPriceRange.categoryName"

			// 메시지가 "propertyPath: 실제 메시지" 형태로 되어 있는지 확인하고, 실제 메시지만 추출
			String expectedPrefix = propertyPathString + ": ";
			if (fullMessage.startsWith(expectedPrefix)) {
				extractedMessage = fullMessage.substring(expectedPrefix.length());
			} else {
				extractedMessage = fullMessage; // 예상한 접두사가 없으면 그냥 전체 메시지 사용
			}
		}

		final var body = new ExceptionMessageResource(extractedMessage);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionMessageResource> handleResourceNotFoundException(final ResourceNotFoundException e) {
		final var body = new ExceptionMessageResource(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ExceptionMessageResource> conflict(final IllegalStateException e) {
		final var body = new ExceptionMessageResource(e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionMessageResource> badRequest(final IllegalArgumentException e) {
		final var body = new ExceptionMessageResource(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionMessageResource> internalServerError(final Exception e) {
		final var body = new ExceptionMessageResource(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}

}
