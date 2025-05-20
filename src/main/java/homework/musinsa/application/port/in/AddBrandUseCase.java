package homework.musinsa.application.port.in;

import homework.musinsa.domain.model.Brand;
import homework.musinsa.infrastructure.adapter.in.rest.request.AddBrandCommand;

public interface AddBrandUseCase {

	Brand addBrand(AddBrandCommand name);

}
