package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.CategoryRequestDto;
import com.microservice.emazon.application.dto.CategoryResponseDto;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;


import java.util.List;

public interface ICategoryHandler {


    /*
     * Get all categories
     * @return List<CategoryResponseDto>
     */
    List<CategoryResponseDto> getAllCategories();

    /*
     * Save a category
     * @param CategoryResponseDto
     */
    void saveCategory(CategoryRequestDto categoryRequestDto);

    // TODO: modificacion 2 - Se agrega el metodo getPagination a la interfaz ICategoryHandler
    /*
     * Get categories by pagination
     * @param paginationUtil
     * @return Pagination<CategoryResponseDto>
     */
    Pagination<CategoryResponseDto> getPagination(PaginationUtil paginationUtil);

}
