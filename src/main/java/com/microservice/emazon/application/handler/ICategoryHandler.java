package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.request.CategoryRequestDto;
import com.microservice.emazon.application.dto.response.CategoryByArticleResponseDto;
import com.microservice.emazon.application.dto.response.CategoryResponseDto;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;


import java.util.List;

/*
* Interface para manejar las categorias
*/
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

    /*
     * Get categories by pagination
     * @param paginationUtil
     * @return Pagination<CategoryResponseDto>
     */
    Pagination<CategoryResponseDto> getAllCategoriesPagination(PaginationUtil paginationUtil);



    List<CategoryByArticleResponseDto> getAllCategoriesByArticleId(Long articleId);


}
