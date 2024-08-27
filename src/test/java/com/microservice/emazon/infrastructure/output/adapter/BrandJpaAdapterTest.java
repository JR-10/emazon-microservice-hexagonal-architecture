package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.infrastructure.exeptions.BrandException;
import com.microservice.emazon.infrastructure.output.entity.BrandEntity;
import com.microservice.emazon.infrastructure.output.mapper.IBrandEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandJpaAdapterTest {

    @Mock
    private IBrandEntityMapper brandEntityMapper;

    @Mock
    private IBrandRepository brandRepository;

    @InjectMocks
    private BrandJpaAdapter brandJpaAdapter;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrandSuccessfully() {
        Brand brand = new Brand(1L, "BrandName", "BrandDescription");
        when(brandRepository.existsByNameIgnoreCase(brand.getName())).thenReturn(false);

        brandJpaAdapter.saveBrand(brand);

        verify(brandRepository, times(1)).existsByNameIgnoreCase(brand.getName());
    }

    @Test
    void saveBrandThrowsExceptionWhenBrandExists() {
        Brand brand = new Brand(1L, "ExistingBrand", "BrandDescription");
        when(brandRepository.existsByNameIgnoreCase(brand.getName())).thenReturn(true);

        assertThrows(BrandException.class, () -> {
            brandJpaAdapter.saveBrand(brand);
        });

        verify(brandRepository, times(1)).existsByNameIgnoreCase(brand.getName());
    }


    @Test
    void getAllBrandsSuccessfully() {
        PaginationUtil paginationUtil = new PaginationUtil(0, 5, "name", true);
        List<BrandEntity> brandEntities = new ArrayList<>();
        Page<BrandEntity> brandPage = new PageImpl<>(brandEntities, PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "name")), 0);
        List<Brand> brands = new ArrayList<>();

        when(brandRepository.findAll(any(PageRequest.class))).thenReturn(brandPage);
        when(brandEntityMapper.toBrandList(brandEntities)).thenReturn(brands);

        Pagination<Brand> result = brandJpaAdapter.getAllBrands(paginationUtil);

        assertEquals(0, result.getTotalPages());
        assertEquals(0, result.getTotalElements());
        assertTrue(result.getData().isEmpty());
        verify(brandRepository, times(1)).findAll(any(PageRequest.class));
        verify(brandEntityMapper, times(1)).toBrandList(brandEntities);
    }

    @Test
    void getAllBrandsWithNonEmptyResult() {
        PaginationUtil paginationUtil = new PaginationUtil(0, 5, "name", true);
        List<BrandEntity> brandEntities = List.of(new BrandEntity(), new BrandEntity());
        Page<BrandEntity> brandPage = new PageImpl<>(brandEntities, PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "name")), 2);
        List<Brand> brands = List.of(new Brand(1L, "BrandName1", "BrandDescription1"), new Brand(2L, "BrandName2", "BrandDescription2"));

        when(brandRepository.findAll(any(PageRequest.class))).thenReturn(brandPage);
        when(brandEntityMapper.toBrandList(brandEntities)).thenReturn(brands);

        Pagination<Brand> result = brandJpaAdapter.getAllBrands(paginationUtil);

        assertEquals(1, result.getTotalPages());
        assertEquals(2, result.getTotalElements());
        assertFalse(result.getData().isEmpty());
        verify(brandRepository, times(1)).findAll(any(PageRequest.class));
        verify(brandEntityMapper, times(1)).toBrandList(brandEntities);
    }

    @Test
    void getAllBrandsWithInvalidPagination() {
        PaginationUtil paginationUtil = new PaginationUtil(-1, 0, "name", true);

        assertThrows(IllegalArgumentException.class, () -> {
            brandJpaAdapter.getAllBrands(paginationUtil);
        });
    }


}