package homework.musinsa.infrastructure.adapter.in.rest.request;

import homework.musinsa.domain.enums.Category;

public record AddProductCommand(int price, Category category, Long brandId) {
}
