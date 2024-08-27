package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.api.IBrandServicePort;
import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.spi.IBrandPersistencePort;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        // revisar validacion si cumple con los croterios de aceptacion
        // aqui es donde va la logica de negocio
        brandPersistencePort.saveBrand(brand);
    }
}
