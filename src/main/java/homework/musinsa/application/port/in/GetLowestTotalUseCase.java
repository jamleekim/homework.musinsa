package homework.musinsa.application.port.in;

import homework.musinsa.domain.model.Product;

import java.util.List;

public interface GetLowestTotalUseCase {

	List<Product> getLowestTotalProducts();

}
