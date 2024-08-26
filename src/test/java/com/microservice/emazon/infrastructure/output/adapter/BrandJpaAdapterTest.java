package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.infrastructure.exeptions.BrandException;
import com.microservice.emazon.infrastructure.output.mapper.IBrandEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.IBrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
}