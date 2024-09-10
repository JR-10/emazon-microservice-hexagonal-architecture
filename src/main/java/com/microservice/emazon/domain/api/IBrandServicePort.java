package com.microservice.emazon.domain.api;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;

public interface IBrandServicePort {

    // guardar una marca
    void saveBrand(Brand brand);

    // obtener todas las marcas paginadas
    Pagination<Brand> getAllBrands(PaginationUtil paginationUtil);

    // obtener una marca por su id
    Brand getBrandById(Long brandId);
}
