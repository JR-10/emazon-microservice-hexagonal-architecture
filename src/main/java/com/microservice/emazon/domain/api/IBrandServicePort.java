package com.microservice.emazon.domain.api;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;

public interface IBrandServicePort {

    void saveBrand(Brand brand);

    Pagination<Brand> getAllBrands(PaginationUtil paginationUtil);

    Brand getBrandById(Long brandId);
}
