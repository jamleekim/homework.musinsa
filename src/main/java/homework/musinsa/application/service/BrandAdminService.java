package homework.musinsa.application.service;

import homework.musinsa.application.port.in.AddBrandUseCase;
import homework.musinsa.application.port.in.DeleteBrandUseCase;
import homework.musinsa.application.port.in.UpdateBrandUseCase;
import homework.musinsa.application.port.out.BrandOutputPort;
import homework.musinsa.domain.model.Brand;
import homework.musinsa.infrastructure.adapter.in.rest.request.AddBrandCommand;
import homework.musinsa.infrastructure.adapter.in.rest.request.DeleteBrandCommand;
import homework.musinsa.infrastructure.adapter.in.rest.request.UpdateBrandCommand;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BrandAdminService implements AddBrandUseCase, UpdateBrandUseCase, DeleteBrandUseCase {

	private final BrandOutputPort brandOutputPort;

	@Override
	public Brand addBrand(AddBrandCommand add) {
		return brandOutputPort.addBrand(add.name());
	}

	@Override
	public void updateBrand(UpdateBrandCommand update) {
		brandOutputPort.getBrand(update.id()); // 존재유무 확인
		brandOutputPort.updateBrand(new Brand(update.id(), update.name()));
	}

	@Override
	public void deleteBrand(DeleteBrandCommand delete) {
		brandOutputPort.getBrand(delete.id()); // 존재유무 확인
		brandOutputPort.deleteBrand(delete.id());
	}

}
