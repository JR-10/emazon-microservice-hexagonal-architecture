package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.infrastructure.exeptions.CategoryException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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
    }


    @Test
    void getAllCategories_ReturnsCategoryList() {
        List<Category> categories = List.of(new Category(1L, "CategoryName", "CategoryDescription"));
        when(categoryPersistencePort.getAllCategories()).thenReturn(categories);

        List<Category> result = categoryUseCase.getAllCategories();

        assertEquals(categories, result);
    }

    @Test
    void getCategories_ReturnsSortedCategoriesAscending() {
        Page<Category> categories = new PageImpl<>(List.of(new Category(1L, "CategoryName", "CategoryDescription")));
        Pageable pageable = PageRequest.of(0, 10);
        when(categoryPersistencePort.getCategories("asc", pageable)).thenReturn(categories);

        Page<Category> result = categoryUseCase.getCategories("asc", pageable);

        assertEquals(categories.getContent(), result.getContent());
    }

    @Test
    void getCategories_ReturnsSortedCategoriesDescending() {
        Page<Category> categories = new PageImpl<>(List.of(new Category(1L, "CategoryName", "CategoryDescription")));
        Pageable pageable = PageRequest.of(0, 10);
        when(categoryPersistencePort.getCategories("desc", pageable)).thenReturn(categories);

        Page<Category> result = categoryUseCase.getCategories("desc", pageable);

        assertEquals(categories.getContent(), result.getContent());
    }



    @Test
    void getCategory_ReturnsEmptyOptionalWhenCategoryNotFound() {
        when(categoryPersistencePort.getCategory(1L)).thenReturn(Optional.empty());

        Optional<Category> result = categoryUseCase.getCategory(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    void saveCategory_SavesCategorySuccessfully() {
        Category category = new Category(1L, "CategoryName", "CategoryDescription");
        doNothing().when(categoryPersistencePort).saveCategory(category);

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    void saveCategory_ThrowsExceptionForInvalidCategory() {
        Category category = new Category(1L, "A very long category name that exceeds the maximum allowed length of fifty characters", "A very long category description that exceeds the maximum allowed length of ninety characters");

        assertThrows(CategoryException.class, () -> categoryUseCase.saveCategory(category));
    }

}