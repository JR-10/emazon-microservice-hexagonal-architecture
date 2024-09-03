package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.application.util.ApplicationConstants;
import com.microservice.emazon.domain.api.IBrandServicePort;
import com.microservice.emazon.domain.exeptions.CategoryExceptions;
import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.IBrandPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.domain.util.ValidationUtil;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        ValidationUtil.validateBrand(brand);
        if (brandPersistencePort.brandExistsByName(brand.getName())) {
            throw new CategoryExceptions.CategoryNameAlreadyExistsException(ApplicationConstants.CATEGORY_NAME_ALREADY_EXISTS_MESSAGE);
        }
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Pagination<Brand> getAllBrands(PaginationUtil paginationUtil) {
        return brandPersistencePort.getAllBrands(paginationUtil);
    }
}
