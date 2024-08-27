package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.BrandRequestDto;
import com.microservice.emazon.application.dto.BrandResponseDto;
import com.microservice.emazon.application.mapper.IBrandDtoMapper;
import com.microservice.emazon.domain.api.IBrandServicePort;
import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

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

    @Test
    void getAllBrandsSuccessfully() {
        PaginationUtil paginationUtil = new PaginationUtil(0, 5, "name", true);
        Pagination<BrandResponseDto> expectedPagination = new Pagination<>(true, 0, 0, 0L, new ArrayList<>());
        when(brandServicePort.getAllBrands(paginationUtil)).thenReturn(new Pagination<>(true, 0, 0, 0L, new ArrayList<>()));
        when(brandMapper.paginationBrandToDto(any(Pagination.class))).thenReturn(expectedPagination);

        Pagination<BrandResponseDto> result = brandHandler.getAllBrands(paginationUtil);

        assertEquals(expectedPagination, result);
        verify(brandServicePort, times(1)).getAllBrands(paginationUtil);
        verify(brandMapper, times(1)).paginationBrandToDto(any(Pagination.class));
    }

    @Test
    void getAllBrandsWithEmptyResult() {
        PaginationUtil paginationUtil = new PaginationUtil(0, 5, "name", true);
        Pagination<BrandResponseDto> emptyPagination = new Pagination<>(true, 0, 0, 0L, new ArrayList<>());
        when(brandServicePort.getAllBrands(paginationUtil)).thenReturn(new Pagination<>(true, 0, 0, 0L, new ArrayList<>()));
        when(brandMapper.paginationBrandToDto(any(Pagination.class))).thenReturn(emptyPagination);

        Pagination<BrandResponseDto> result = brandHandler.getAllBrands(paginationUtil);

        assertTrue(result.getData().isEmpty());
        verify(brandServicePort, times(1)).getAllBrands(paginationUtil);
        verify(brandMapper, times(1)).paginationBrandToDto(any(Pagination.class));
    }

    @Test
    void getAllBrandsWithInvalidPagination() {
        PaginationUtil paginationUtil = new PaginationUtil(-1, 0, "name", true);
        when(brandServicePort.getAllBrands(paginationUtil)).thenThrow(new IllegalArgumentException("Invalid pagination parameters"));

        assertThrows(IllegalArgumentException.class, () -> {
            brandHandler.getAllBrands(paginationUtil);
        });
        verify(brandServicePort, times(1)).getAllBrands(paginationUtil);
    }
}