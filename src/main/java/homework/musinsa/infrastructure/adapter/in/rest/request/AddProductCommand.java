package homework.musinsa.infrastructure.adapter.in.rest.request;

import homework.musinsa.domain.enums.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddProductCommand(
		@Min(value = 10, message = "가격은 10원 이상이어야 합니다.") int price,
		@NotNull(message = "카테고리는 필수입니다.") Category category,
		@NotNull(message = "브랜드 ID는 필수입니다.") Long brandId) {
}
