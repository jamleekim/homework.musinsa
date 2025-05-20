package homework.musinsa.infrastructure.adapter.in.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateBrandCommand(@Schema(hidden = true) long id, String name) {

	public UpdateBrandCommand toCommand(long id) {
		return new UpdateBrandCommand(id, name);
	}

}
