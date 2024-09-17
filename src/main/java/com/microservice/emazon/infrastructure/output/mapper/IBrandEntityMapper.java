package com.microservice.emazon.infrastructure.output.mapper;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.infrastructure.output.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {

    BrandEntity toBrandEntity(Brand brand);

    Brand toBrand(BrandEntity brandEntity);

    List<Brand> toBrandList(List<BrandEntity> brandEntityList);


    @Named("idToBrandEntity")
    default BrandEntity idToBrandEntity (Long brandId) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brandId);
        return brandEntity;
    }
}
