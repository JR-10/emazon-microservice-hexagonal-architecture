package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.infrastructure.output.entity.CategoryEntity;
import com.microservice.emazon.infrastructure.output.mapper.ICategoryEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class CategoryJpaAdapterTest {


    @Mock
    private ICategoryEntityMapper categoryEntityMapper;

    @Mock
    private ICategoryRepository categoryRepository;

    @InjectMocks
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories_ReturnsCategoryList() {
        List<CategoryEntity> categoryEntities = List.of(new CategoryEntity());
        List<Category> categories = List.of(new Category(1L, "CategoryName", "CategoryDescription"));

        when(categoryRepository.findAll()).thenReturn(categoryEntities);
        when(categoryEntityMapper.toCategoryList(categoryEntities)).thenReturn(categories);

        List<Category> result = categoryJpaAdapter.getAllCategories();

        assertEquals(categories, result);
    }





    @Test
    void saveCategory_SavesCategorySuccessfully() {
        Category category = new Category(1L, "CategoryName", "CategoryDescription");
        CategoryEntity categoryEntity = new CategoryEntity();

        when(categoryRepository.existsByName(anyString())).thenReturn(false);
        when(categoryEntityMapper.categoryEntityToCategory(category)).thenReturn(categoryEntity);

        categoryJpaAdapter.saveCategory(category);

        verify(categoryRepository, times(1)).save(categoryEntity);
    }




    @Test
    void getCategory_ReturnsEmptyOptional() {
        Optional<Category> result = categoryJpaAdapter.getCategory(1L);

        assertTrue(result.isEmpty());
    }
}