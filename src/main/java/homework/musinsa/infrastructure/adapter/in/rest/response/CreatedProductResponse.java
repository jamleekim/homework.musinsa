package homework.musinsa.infrastructure.adapter.in.rest.response;

import homework.musinsa.domain.enums.Category;

public record CreatedProductResponse(long id, int price, Category category, String brandName) {
}
