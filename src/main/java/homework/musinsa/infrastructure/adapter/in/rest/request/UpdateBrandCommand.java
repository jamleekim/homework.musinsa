package homework.musinsa.infrastructure.adapter.in.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UpdateBrandCommand(@Schema(hidden = true) long id, @NotNull(message = "브랜드명은 필수입니다.") String name) {

	public UpdateBrandCommand toCommand(long id) {
		return new UpdateBrandCommand(id, name);
	}

}
