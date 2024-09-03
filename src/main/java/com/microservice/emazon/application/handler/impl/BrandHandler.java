package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.request.BrandRequestDto;
import com.microservice.emazon.application.dto.response.BrandResponseDto;
import com.microservice.emazon.application.handler.IBrandHandler;
import com.microservice.emazon.application.mapper.IBrandDtoMapper;
import com.microservice.emazon.domain.api.IBrandServicePort;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;
    private final IBrandDtoMapper brandMapper;

    @Override
    public void saveBrand(BrandRequestDto brandRequestDto) {
        brandServicePort.saveBrand(brandMapper.brandDtoToBrand(brandRequestDto));
    }

    @Override
    public Pagination<BrandResponseDto> getAllBrands(PaginationUtil paginationUtil) {
        return brandMapper.paginationBrandToDto(brandServicePort.getAllBrands(paginationUtil));
    }
}
