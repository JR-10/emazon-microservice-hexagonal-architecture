package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.CategoryRequestDto;
import com.microservice.emazon.application.dto.CategoryResponseDto;
import com.microservice.emazon.application.mapper.ICategoryDtoMapper;
import com.microservice.emazon.domain.api.ICategoryServicePort;
import com.microservice.emazon.domain.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryHandlerTest {


    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryDtoMapper categoryMapper;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories_ReturnsCategoryResponseDtoList() {
        List<Category> categories = List.of(new Category(1L, "CategoryName", "CategoryDescription"));
        List<CategoryResponseDto> categoryResponseDtos = List.of(new CategoryResponseDto(1L, "CategoryName", "CategoryDescription"));

        when(categoryServicePort.getAllCategories()).thenReturn(categories);
        when(categoryMapper.categoryListToCategoryDtoList(categories)).thenReturn(categoryResponseDtos);

        List<CategoryResponseDto> result = categoryHandler.getAllCategories();

        assertEquals(categoryResponseDtos, result);
    }


    @Test
    void saveCategory_SavesCategorySuccessfully() {
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto("CategoryName", "CategoryDescription");
        Category category = new Category(1L, "CategoryName", "CategoryDescription");

        when(categoryMapper.categoryDtoToCategory(categoryRequestDto)).thenReturn(category);

        categoryHandler.saveCategory(categoryRequestDto);

        verify(categoryServicePort, times(1)).saveCategory(category);
    }

}