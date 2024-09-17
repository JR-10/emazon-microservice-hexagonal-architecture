package com.microservice.emazon.application.mapper;

import com.microservice.emazon.application.dto.request.CategoryRequestDto;
import com.microservice.emazon.application.dto.response.CategoryByArticleResponseDto;
import com.microservice.emazon.application.dto.response.CategoryResponseDto;
import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;



@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryDtoMapper {


    List<CategoryResponseDto> categoryListToCategoryDtoList(List<Category> categories);

    Category categoryDtoToCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto categoryToCategoryDto(Category category);

    Pagination<CategoryResponseDto> paginationToCategoryResponseDto(Pagination<Category> pagination);

    List<CategoryByArticleResponseDto> categoryListToCategoryByArticleDtoList(List<Category> categories);

}
