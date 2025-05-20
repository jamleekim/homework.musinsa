package homework.musinsa.application.port.in;

import homework.musinsa.domain.model.Product;
import org.apache.commons.lang3.tuple.Pair;

public interface GetCategoryPriceRangeUseCase {

	Pair<Product, Product> getCategoryPriceRange(String categoryName);

}
