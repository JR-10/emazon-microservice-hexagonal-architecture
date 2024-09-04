package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.request.BrandRequestDto;
import com.microservice.emazon.application.dto.response.BrandResponseDto;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;


public interface IBrandHandler {

    void saveBrand(BrandRequestDto brandRequestDto);

    Pagination<BrandResponseDto> getAllBrands(PaginationUtil paginationUtil);
}
