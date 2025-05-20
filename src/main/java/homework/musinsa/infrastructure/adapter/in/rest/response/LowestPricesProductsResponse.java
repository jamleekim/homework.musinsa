package homework.musinsa.infrastructure.adapter.in.rest.response;

import homework.musinsa.domain.model.Product;

import java.util.List;

public record LowestPricesProductsResponse(List<Product> products, long totalPrice) {

	public static LowestPricesProductsResponse from(List<Product> products) {
		final var totalPrice = products.stream().mapToLong(Product::price).sum();
		return new LowestPricesProductsResponse(products, totalPrice);
	}

}
