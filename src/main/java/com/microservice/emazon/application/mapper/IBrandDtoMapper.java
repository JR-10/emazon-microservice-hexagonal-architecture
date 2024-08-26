package com.microservice.emazon.application.mapper;

import com.microservice.emazon.application.dto.BrandRequestDto;
import com.microservice.emazon.application.dto.BrandResponseDto;
import com.microservice.emazon.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandDtoMapper {


    Brand brandDtoToBrand(BrandRequestDto brandRequestDto);

    BrandResponseDto brandToBrandDto(Brand brand);

}
