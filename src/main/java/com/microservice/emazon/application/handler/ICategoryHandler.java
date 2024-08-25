package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.CategoryRequestDto;
import com.microservice.emazon.application.dto.CategoryResponseDto;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;


import java.util.List;

public interface ICategoryHandler {


    /*
     * Get all categories
     * @return List<CategoryDto>
     */
    List<CategoryResponseDto> getAllCategories();

    /*
     * Save a category
     * @param categoryDto
     */
    void saveCategory(CategoryRequestDto categoryRequestDto);

    // TODO: modificacion 2 - Se agrega el metodo getPagination a la interfaz ICategoryHandler
    // como se quiere retornar la respuesta
    List<CategoryResponseDto> getCategoriesByPagination(int pageNo, int pageSize, String sortBy, String order);


    Pagination<CategoryResponseDto> getPagination(PaginationUtil paginationUtil);

}
