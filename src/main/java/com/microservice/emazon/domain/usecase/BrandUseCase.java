package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.api.IBrandServicePort;
import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.IBrandPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brand == null) {
            throw new NullPointerException("Brand cannot be null");
        }
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Pagination<Brand> getAllBrands(PaginationUtil paginationUtil) {
        return brandPersistencePort.getAllBrands(paginationUtil);
    }
}
