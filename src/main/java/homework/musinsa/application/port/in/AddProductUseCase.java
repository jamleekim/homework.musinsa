package homework.musinsa.application.port.in;

import homework.musinsa.domain.model.Product;
import homework.musinsa.infrastructure.adapter.in.rest.request.AddProductCommand;

public interface AddProductUseCase {
	Product create(AddProductCommand command);
}
