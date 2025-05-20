package homework.musinsa.application.port.out;

import homework.musinsa.domain.enums.Category;
import homework.musinsa.domain.model.Product;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface ProductOutputPort {

	List<Product> getLowestPricesProducts();

	List<Product> getLowestTotalProducts();

	Pair<Product, Product> getCategoryPriceRange(Category category);

	Product getProduct(Long productId);

	Product create(Product product);

	void update(Product product);

	void delete(Long productId);

}
