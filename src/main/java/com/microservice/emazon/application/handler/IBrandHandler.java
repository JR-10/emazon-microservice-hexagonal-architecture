package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.BrandRequestDto;
import com.microservice.emazon.application.dto.BrandResponseDto;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;


/*
* Interface para manipulacion de marcas
*/
public interface IBrandHandler {

    void saveBrand(BrandRequestDto brandRequestDto);

    Pagination<BrandResponseDto> getAllBrands(PaginationUtil paginationUtil);
}
