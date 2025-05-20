package homework.musinsa.domain.model;

import homework.musinsa.domain.enums.Category;
import lombok.Builder;

@Builder
public record Product(
		int price, Category category, String brandName, long brandId, long productId
) {
}
