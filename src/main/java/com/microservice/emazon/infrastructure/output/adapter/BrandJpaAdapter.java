package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.IBrandPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.infrastructure.output.entity.BrandEntity;
import com.microservice.emazon.infrastructure.output.mapper.IBrandEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BrandJpaAdapter  implements IBrandPersistencePort {

    private final IBrandEntityMapper brandEntityMapper;
    private final IBrandRepository brandRepository;


    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));
    }

    @Override
    public boolean brandExistsByName(String brandName) {
        return brandRepository.existsByName(brandName);
    }

    @Override
    public Pagination<Brand> getAllBrands(PaginationUtil paginationUtil) {
        Sort.Direction sortDirection = paginationUtil.isAscending()? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pageBrandRequest = PageRequest.of(paginationUtil.getPageNumber(), paginationUtil.getPageSize(), Sort.by(sortDirection, paginationUtil.getNameFilter()));
        Page<BrandEntity> brandPage = brandRepository.findAll(pageBrandRequest);
        List<Brand> brands = brandEntityMapper.toBrandList(brandPage.getContent());
        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                brandPage.getTotalPages(),
                brandPage.getTotalElements(),
                brands
        );
    }

    @Override
    public Brand getBrandById(Long brandId) {
        return brandRepository.findById(brandId)
                .map(brandEntityMapper::toBrand)
                .orElse(null);
    }
}
