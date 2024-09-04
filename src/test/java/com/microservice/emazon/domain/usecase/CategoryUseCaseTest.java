package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        // test
    }


    @Test
    void getAllCategories_ReturnsCategoryList() {
        List<Category> categories = List.of(new Category(1L, "CategoryName", "CategoryDescription"));
        when(categoryPersistencePort.getAllCategories()).thenReturn(categories);

        List<Category> result = categoryUseCase.getAllCategories();

        assertEquals(categories, result);
    }


    @Test
    void saveCategory_SavesCategorySuccessfully() {
        Category category = new Category(1L, "CategoryName", "CategoryDescription");
        doNothing().when(categoryPersistencePort).saveCategory(category);

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

}