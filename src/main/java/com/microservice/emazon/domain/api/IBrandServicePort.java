package com.microservice.emazon.domain.api;

import com.microservice.emazon.domain.model.Brand;

public interface IBrandServicePort {

    void saveBrand(Brand brand);
}
