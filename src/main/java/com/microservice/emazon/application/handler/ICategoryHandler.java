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
     * Save a category
     * @param categoryDto
     */
    void saveCategory(CategoryRequestDto categoryRequestDto);

    // TODO: modificacion 2 - Se agrega el metodo getPagination a la interfaz ICategoryHandler
    // como se quiere retornar la respuesta
    List<CategoryResponseDto> getCategoriesByPagination(int pageNo, int pageSize, String sortBy, String order);

}
