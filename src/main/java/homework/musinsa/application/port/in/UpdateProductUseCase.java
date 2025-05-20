package homework.musinsa.application.port.in;

import homework.musinsa.infrastructure.adapter.in.rest.request.UpdateProductCommand;

public interface UpdateProductUseCase {
	void update(UpdateProductCommand command);
}
