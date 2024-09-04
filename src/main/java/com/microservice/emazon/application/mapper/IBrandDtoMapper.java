package com.microservice.emazon.application.mapper;

import com.microservice.emazon.application.dto.request.BrandRequestDto;
import com.microservice.emazon.application.dto.response.BrandResponseDto;
import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandDtoMapper {


    Brand brandDtoToBrand(BrandRequestDto brandRequestDto);

    BrandResponseDto brandToBrandDto(Brand brand);

    Pagination<BrandResponseDto> paginationBrandToDto(Pagination<Brand> pagination);
}
