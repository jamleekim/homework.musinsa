package homework.musinsa.application.port.in;

import homework.musinsa.infrastructure.adapter.in.rest.request.UpdateBrandCommand;

public interface UpdateBrandUseCase {

	void updateBrand(UpdateBrandCommand update);

}
