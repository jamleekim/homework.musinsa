package homework.musinsa.infrastructure.adapter.in.rest.request;

import jakarta.validation.constraints.NotNull;

public record AddBrandCommand(@NotNull(message = "브랜드명은 필수입니다.") String name) {
}
