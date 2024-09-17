package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.request.CategoryRequestDto;
import com.microservice.emazon.application.dto.response.CategoryByArticleResponseDto;
import com.microservice.emazon.application.dto.response.CategoryResponseDto;
import com.microservice.emazon.application.handler.ICategoryHandler;
import com.microservice.emazon.application.mapper.ICategoryDtoMapper;
import com.microservice.emazon.domain.api.ICategoryServicePort;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryDtoMapper categoryMapper;



    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryMapper.categoryListToCategoryDtoList(categoryServicePort.getAllCategories());
    }



    @Override
    public void saveCategory(CategoryRequestDto categoryRequestDto) {
        categoryServicePort.saveCategory(categoryMapper.categoryDtoToCategory(categoryRequestDto));
    }



    @Override
    public Pagination<CategoryResponseDto> getAllCategoriesPagination(PaginationUtil paginationUtil) {
        return categoryMapper.paginationToCategoryResponseDto(categoryServicePort.getAllCategoriesPagination(paginationUtil));
    }


    @Override
    public List<CategoryByArticleResponseDto> getAllCategoriesByArticleId(Long articleId) {
        return categoryMapper.categoryListToCategoryByArticleDtoList(categoryServicePort.getAllByArticle(articleId));
    }


}
