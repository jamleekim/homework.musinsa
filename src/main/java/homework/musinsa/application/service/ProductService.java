package homework.musinsa.application.service;

import homework.musinsa.application.port.in.GetCategoryPriceRangeUseCase;
import homework.musinsa.application.port.in.GetLowestPricesProductsUseCase;
import homework.musinsa.application.port.in.GetLowestTotalUseCase;
import homework.musinsa.application.port.out.ProductOutputPort;
import homework.musinsa.domain.enums.Category;
import homework.musinsa.domain.model.Product;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

@AllArgsConstructor
public class ProductService implements GetLowestPricesProductsUseCase, GetLowestTotalUseCase, GetCategoryPriceRangeUseCase {

	private final ProductOutputPort productOutputPort;

	@Override
	public List<Product> getLowestPricesProducts() {
		final var list = productOutputPort.getLowestPricesProducts();

		if(list.isEmpty()) {
			throw new IllegalStateException("No products found");
		}

		return list;
	}

	@Override
	public List<Product> getLowestTotalProducts() {
		return productOutputPort.getLowestTotalProducts();
	}

	@Override
	public Pair<Product, Product> getCategoryPriceRange(String categoryName) {
		final var category = Category.fromDisplayName(categoryName);
		return productOutputPort.getCategoryPriceRange(category);
	}

}
