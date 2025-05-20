package homework.musinsa.application.port.in;

import homework.musinsa.infrastructure.adapter.in.rest.request.DeleteBrandCommand;

public interface DeleteBrandUseCase {

	void deleteBrand(DeleteBrandCommand delete);

}
