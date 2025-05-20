package homework.musinsa.infrastructure.adapter.in.rest.request;

import homework.musinsa.domain.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateProductCommand(int price, Category category, Long brandId, @Schema(hidden = true) Long productId) {

	public UpdateProductCommand toCommand(Long productId) {
		return new UpdateProductCommand(price, category, brandId, productId);
	}

}
