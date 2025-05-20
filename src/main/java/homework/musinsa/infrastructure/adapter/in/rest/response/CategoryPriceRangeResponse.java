package homework.musinsa.infrastructure.adapter.in.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import homework.musinsa.domain.model.Product;
import org.apache.commons.lang3.tuple.Pair;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public record CategoryPriceRangeResponse (
	@JsonProperty("카테고리") String category,
	@JsonProperty("최저가") List<Price> lowest,
	@JsonProperty("최고가") List<Price> highest
) {

		public static CategoryPriceRangeResponse from(Pair<Product, Product> pair) {
			Product low  = pair.getLeft();
			Product high = pair.getRight();

			String category = low.category().toString();

			NumberFormat nf = NumberFormat.getNumberInstance(Locale.KOREA);
			String lowPriceStr  = nf.format(low.price());
			String highPriceStr = nf.format(high.price());

			Price lowPrice  = new Price(low.brandName(), lowPriceStr);
			Price highPrice = new Price(high.brandName(), highPriceStr);

			return new CategoryPriceRangeResponse(
					category,
					List.of(lowPrice),
					List.of(highPrice)
			);
		}

		public record Price(
				@JsonProperty("브랜드") String brand,
				@JsonProperty("가격") String price
		) {}

}
