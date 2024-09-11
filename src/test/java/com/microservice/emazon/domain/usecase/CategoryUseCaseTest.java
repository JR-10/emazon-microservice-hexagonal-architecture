package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
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


    @Test
    void getAllCategoriesPagination_ReturnsPaginatedCategories() {
        PaginationUtil paginationUtil = new PaginationUtil(1, 10, "nameCategory", true);
        Pagination<Category> expectedPagination = new Pagination<>(true, 1, 1, 10, List.of(new Category(1L, "CategoryName", "CategoryDescription")));
        when(categoryPersistencePort.getAllCategoriesPagination(paginationUtil)).thenReturn(expectedPagination);

        Pagination<Category> result = categoryUseCase.getAllCategoriesPagination(paginationUtil);

        assertEquals(expectedPagination, result);
    }

    @Test
    void getAllCategoriesPagination_ReturnsEmptyPagination() {
        PaginationUtil paginationUtil = new PaginationUtil(1, 10, "nameCategory", true);
        Pagination<Category> expectedPagination = new Pagination<>(true, 1, 0, 0, List.of());
        when(categoryPersistencePort.getAllCategoriesPagination(paginationUtil)).thenReturn(expectedPagination);

        Pagination<Category> result = categoryUseCase.getAllCategoriesPagination(paginationUtil);

        assertEquals(expectedPagination, result);
    }


    @Test
    void getAllByProduct_ReturnsCategoryList() {
        Long productId = 1L;
        List<Category> categories = List.of(new Category(1L, "CategoryName", "CategoryDescription"));
        when(categoryPersistencePort.getAllByArticle(productId)).thenReturn(categories);

        List<Category> result = categoryUseCase.getAllByProduct(productId);

        assertEquals(categories, result);
    }

    @Test
    void getAllByProduct_ReturnsEmptyList() {
        Long productId = 1L;
        List<Category> categories = List.of();
        when(categoryPersistencePort.getAllByArticle(productId)).thenReturn(categories);

        List<Category> result = categoryUseCase.getAllByProduct(productId);

        assertTrue(result.isEmpty());
    }


}