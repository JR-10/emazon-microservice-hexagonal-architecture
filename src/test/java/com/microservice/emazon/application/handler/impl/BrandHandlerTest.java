package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.BrandRequestDto;
import com.microservice.emazon.application.mapper.IBrandDtoMapper;
import com.microservice.emazon.domain.api.IBrandServicePort;
import com.microservice.emazon.domain.model.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandHandlerTest {

    @Mock
    private IBrandServicePort brandServicePort;

    @Mock
    private IBrandDtoMapper brandMapper;

    @InjectMocks
    private BrandHandler brandHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveBrandSuccessfully() {
        BrandRequestDto brandRequestDto = new BrandRequestDto("BrandName", "BrandDescription");
        Brand brand = new Brand(1L, "BrandName", "BrandDescription" );

        when(brandMapper.brandDtoToBrand(brandRequestDto)).thenReturn(brand);
        doNothing().when(brandServicePort).saveBrand(any(Brand.class));

        brandHandler.saveBrand(brandRequestDto);

        verify(brandServicePort, times(1)).saveBrand(any(Brand.class));
    }

    @Test
    void saveBrandThrowsExceptionWhenDtoIsNull() {
        BrandRequestDto brandRequestDto = new BrandRequestDto("BrandName", null);
        when(brandMapper.brandDtoToBrand(brandRequestDto)).thenThrow(new IllegalArgumentException("Brand description cannot be null"));

        assertThrows(IllegalArgumentException.class, () -> {
            brandHandler.saveBrand(brandRequestDto);
        });
    }

    @Test
    void saveBrandThrowsExceptionWhenBrandNameIsEmpty() {
        BrandRequestDto brandRequestDto = new BrandRequestDto(" ", "");
        when(brandMapper.brandDtoToBrand(brandRequestDto)).thenThrow(new IllegalArgumentException("Brand name cannot be empty"));

        assertThrows(IllegalArgumentException.class, () -> {
            brandHandler.saveBrand(brandRequestDto);
        });
    }
}