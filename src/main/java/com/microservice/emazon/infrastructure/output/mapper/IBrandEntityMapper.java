package com.microservice.emazon.infrastructure.output.mapper;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.infrastructure.output.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {

    // convierte un objeto de tipo Brand a un objeto de tipo BrandEntity
    BrandEntity toBrandEntity(Brand brand);

    // convierte un objeto de tipo BrandEntity a un objeto de tipo Brand
    Brand toBrand(BrandEntity brandEntity);

    // convierte una lista de objetos de tipo BrandEntity a una lista de objetos de tipo Brand
    List<Brand> toBrandList(List<BrandEntity> brandEntityList);


    // toma un id de tipo Long y lo convierte en un objeto de tipo BrandEntity
    @Named("idToBrandEntity")
    default BrandEntity idToBrandEntity (Long brandId) {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brandId);
        return brandEntity;
    }
}
