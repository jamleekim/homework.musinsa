package homework.musinsa.application.port.out;

import homework.musinsa.domain.model.Brand;

public interface BrandOutputPort {

	Brand getBrand(long id);

	Brand addBrand(String name);

	void updateBrand(Brand brand);

	void deleteBrand(long id);

}
