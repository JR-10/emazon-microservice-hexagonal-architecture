package com.microservice.emazon.domain.spi;

import com.microservice.emazon.domain.model.Brand;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);
}
