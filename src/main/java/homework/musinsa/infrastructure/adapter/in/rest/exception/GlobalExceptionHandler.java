package homework.musinsa.infrastructure.adapter.in.rest.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionMessageResource> notFound(final EntityNotFoundException e) {
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
