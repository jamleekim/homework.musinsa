package homework.musinsa.infrastructure.adapter.in.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import homework.musinsa.domain.model.Product;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public record LowestTotalResponse(@JsonProperty("최저가") LowestPriceData data) {

	private static final NumberFormat NF = NumberFormat.getNumberInstance(Locale.KOREA);

	public static LowestTotalResponse from(List<Product> products) {
		// 1) 브랜드 추출
		String brand = products.stream()
				.findFirst()
				.map(Product::brandName)
				.orElse("");

		// 2) 카테고리별 가격 리스트 (rank 순으로 정렬)
		List<CategoryPriceResponse> categories = products.stream()
				.sorted(Comparator.comparing(p -> p.category().getRank()))
				.map(p -> new CategoryPriceResponse(
						p.category().toString(),      // enum toString()이 한글명 반환
						NF.format(p.price())          // ex. "10,100"
				))
				.collect(Collectors.toList());

		// 3) 총액 계산
		String total = NF.format(
				products.stream()
						.mapToInt(Product::price)
						.sum()
		);

		LowestPriceData data = new LowestPriceData(brand, categories, total);
		return new LowestTotalResponse(data);
	}

	public record CategoryPriceResponse(
			@JsonProperty("카테고리") String category,
			@JsonProperty("가격")    String  price
	) {}

	public record LowestPriceData(
			@JsonProperty("브랜드")    String                    brand,
			@JsonProperty("카테고리") List<CategoryPriceResponse> categories,
			@JsonProperty("총액")      String                    total
	) {}

}
