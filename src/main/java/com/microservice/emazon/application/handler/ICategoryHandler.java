package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.CategoryRequestDto;
import com.microservice.emazon.application.dto.CategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryHandler {


    /*
     * Get all categories
     * @return List<CategoryDto>
     */
    List<CategoryResponseDto> getAllCategories();

    /*
     * Get categories
     * @param order
     * @param pageable
     * @return Page<CategoryDto>
     */
    Page<CategoryResponseDto> getCategories(String order, Pageable pageable);

    /*
     * Save a category
     * @param categoryDto
     */
    void saveCategory(CategoryRequestDto categoryRequestDto);
}
