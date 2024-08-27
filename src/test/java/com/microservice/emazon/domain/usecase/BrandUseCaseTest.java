package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.IBrandPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBrandSuccessfully() {
        Brand brand = new Brand(1L, "BrandName", "BrandDescription");
        doNothing().when(brandPersistencePort).saveBrand(any(Brand.class));

        brandUseCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(any(Brand.class));
    }

    @Test
    void saveBrandThrowsExceptionWhenBrandIsNull() {
        assertThrows(NullPointerException.class, () -> {
            brandUseCase.saveBrand(null);
        });
    }

    @Test
    void saveBrandThrowsExceptionWhenBrandNameIsEmpty() {
        Brand brand = new Brand(1L, "", "BrandDescription");
        doThrow(new IllegalArgumentException("Brand name cannot be empty")).when(brandPersistencePort).saveBrand(any(Brand.class));

        assertThrows(IllegalArgumentException.class, () -> {
            brandUseCase.saveBrand(brand);
        });
    }

    @Test
    void saveBrandThrowsExceptionWhenBrandDescriptionIsNull() {
        Brand brand = new Brand(1L, "BrandName", null);
        doThrow(new IllegalArgumentException("Brand description cannot be null")).when(brandPersistencePort).saveBrand(any(Brand.class));

        assertThrows(IllegalArgumentException.class, () -> {
            brandUseCase.saveBrand(brand);
        });
    }

    /*
    @Test
    void getAllBrandsSuccessfully() {
        PaginationUtil paginationUtil = new PaginationUtil(0, 5, "name", true);
        Pagination<Brand> expectedPagination = new Pagination<>(true, 0, 0, 0L, new ArrayList<>());
        when(brandPersistencePort.getAllBrands(paginationUtil)).thenReturn(new Pagination<>(true, 0, 0, 0L, new ArrayList<>()));

        Pagination<Brand> result = brandUseCase.getAllBrands(paginationUtil);
        assertEquals(expectedPagination, result);
        verify(brandPersistencePort, times(1)).getAllBrands(paginationUtil);
    }
    */


    @Test
    void getAllBrandsWithEmptyResult() {
        PaginationUtil paginationUtil = new PaginationUtil(0, 5, "name", true);
        Pagination<Brand> emptyPagination = new Pagination<>(true, 0, 0, 0L, new ArrayList<>());
        when(brandPersistencePort.getAllBrands(paginationUtil)).thenReturn(emptyPagination);

        Pagination<Brand> result = brandUseCase.getAllBrands(paginationUtil);

        assertTrue(result.getData().isEmpty());
        verify(brandPersistencePort, times(1)).getAllBrands(paginationUtil);
    }

    @Test
    void getAllBrandsWithInvalidPagination() {
        PaginationUtil paginationUtil = new PaginationUtil(-1, 0, "name", true);
        when(brandPersistencePort.getAllBrands(paginationUtil)).thenThrow(new IllegalArgumentException("Invalid pagination parameters"));

        assertThrows(IllegalArgumentException.class, () -> {
            brandUseCase.getAllBrands(paginationUtil);
        });
        verify(brandPersistencePort, times(1)).getAllBrands(paginationUtil);
    }
}