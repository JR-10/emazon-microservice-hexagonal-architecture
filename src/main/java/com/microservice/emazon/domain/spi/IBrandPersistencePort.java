package com.microservice.emazon.domain.spi;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;

public interface IBrandPersistencePort {

    void saveBrand(Brand brand);

    Pagination<Brand> getAllBrands(PaginationUtil paginationUtil);
}
