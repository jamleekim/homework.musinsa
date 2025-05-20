package homework.musinsa.application.port.in;

import homework.musinsa.domain.model.Product;

import java.util.List;

public interface GetLowestPricesProductsUseCase {

	List<Product> getLowestPricesProducts();

}
