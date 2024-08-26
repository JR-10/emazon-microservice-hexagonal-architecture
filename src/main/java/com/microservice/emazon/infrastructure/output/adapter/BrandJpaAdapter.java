package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.spi.IBrandPersistencePort;
import com.microservice.emazon.infrastructure.exeptions.BrandException;
import com.microservice.emazon.infrastructure.output.mapper.IBrandEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandJpaAdapter  implements IBrandPersistencePort {

    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandRepository brandRepository;


    @Override
    public void saveBrand(Brand brand) {
        // validacion si existe la marca creada con el mismo nombre
        if (brandRepository.existsByNameIgnoreCase(brand.getName())) {
            throw new BrandException("Marca existente");
        }
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));
    }
}
