package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.CategoryRequestDto;
import com.microservice.emazon.application.dto.CategoryResponseDto;
import com.microservice.emazon.application.handler.ICategoryHandler;
import com.microservice.emazon.application.mapper.ICategoryDtoMapper;
import com.microservice.emazon.domain.api.ICategoryServicePort;
import com.microservice.emazon.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private Page<CategoryResponseDto> mapCategoryPageToCategoryDtoPage(Page<Category> categories) {
        return categories.map(categoryMapper::categoryToCategoryDto);
    }

    @Override
    public void saveCategory(CategoryRequestDto categoryRequestDto) {
        categoryServicePort.saveCategory(categoryMapper.categoryDtoToCategory(categoryRequestDto));

    }

    // TODO: modificacion 3 - Se agrega el metodo getPagination implementando la interfaz ICategoryHandler
    @Override
    public List<CategoryResponseDto> getCategoriesByPagination(int pageNo, int pageSize, String sortBy, String order) {

        Sort sort = Sort.by(Sort.Direction.fromString(order), sortBy);
        Pageable pageRequest = PageRequest.of(pageNo, pageSize, sort);
        List<CategoryResponseDto> paginationCategory;
        paginationCategory = categoryMapper.categoryListToCategoryDtoList(categoryServicePort.getCategoriesByPagination(pageRequest));

        return paginationCategory;
    }
}
