// TODO: 3 - Define the interface IBrandHandler with the method saveBrand(BrandRequestDto brandRequestDto)
package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.BrandRequestDto;
import com.microservice.emazon.application.dto.BrandResponseDto;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;

public interface IBrandHandler {

    void saveBrand(BrandRequestDto brandRequestDto);

    Pagination<BrandResponseDto> getAllBrands(PaginationUtil paginationUtil);
}
