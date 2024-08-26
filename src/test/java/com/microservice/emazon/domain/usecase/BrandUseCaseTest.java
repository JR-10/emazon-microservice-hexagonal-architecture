package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
    }


    void saveBrandSuccessfully() {
        Brand brand = new Brand(1L, "BrandName", "BrandDescription");
        doNothing().when(brandPersistencePort).saveBrand(any(Brand.class));

        brandUseCase.saveBrand(brand);

        verify(brandPersistencePort, times(1)).saveBrand(any(Brand.class));
    }

    void saveBrandThrowsExceptionWhenBrandIsNull() {
        assertThrows(NullPointerException.class, () -> {
            brandUseCase.saveBrand(null);
        });
    }

    void saveBrandThrowsExceptionWhenBrandNameIsEmpty() {
        Brand brand = new Brand(1L, "", "BrandDescription");
        doThrow(new IllegalArgumentException("Brand name cannot be empty")).when(brandPersistencePort).saveBrand(any(Brand.class));

        assertThrows(IllegalArgumentException.class, () -> {
            brandUseCase.saveBrand(brand);
        });
    }

    void saveBrandThrowsExceptionWhenBrandDescriptionIsNull() {
        Brand brand = new Brand(1L, "BrandName", null);
        doThrow(new IllegalArgumentException("Brand description cannot be null")).when(brandPersistencePort).saveBrand(any(Brand.class));

        assertThrows(IllegalArgumentException.class, () -> {
            brandUseCase.saveBrand(brand);
        });
    }
}