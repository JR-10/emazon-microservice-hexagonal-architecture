package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.request.CategoryRequestDto;
import com.microservice.emazon.application.dto.response.CategoryByArticleResponseDto;
import com.microservice.emazon.application.dto.response.CategoryResponseDto;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;


import java.util.List;


public interface ICategoryHandler {

    List<CategoryResponseDto> getAllCategories();

    void saveCategory(CategoryRequestDto categoryRequestDto);

    Pagination<CategoryResponseDto> getAllCategoriesPagination(PaginationUtil paginationUtil);

    List<CategoryByArticleResponseDto> getAllCategoriesByArticleId(Long articleId);


}
