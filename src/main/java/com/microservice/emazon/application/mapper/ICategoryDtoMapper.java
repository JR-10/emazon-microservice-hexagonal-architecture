package com.microservice.emazon.application.mapper;

import com.microservice.emazon.application.dto.CategoryRequestDto;
import com.microservice.emazon.application.dto.CategoryResponseDto;
import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
/*
 * Convert Category to CategoryDto and vice versa
 */
public interface ICategoryDtoMapper {

    /*
     * Convert List<Category> to List<CategoryResponseDto>
     * @param categories
     * @return List<CategoryResponseDto>
     */
    List<CategoryResponseDto> categoryListToCategoryDtoList(List<Category> categories);


    /*
     * Convert CategoryDto to Category
     * @param categoryDto
     * @return Category
     */
    Category categoryDtoToCategory(CategoryRequestDto categoryRequestDto);


    /*
     * Convert Category to CategoryResponseDto
     * @param category
     * @return CategoryResponseDto
     */
    CategoryResponseDto categoryToCategoryDto(Category category);


    Pagination<CategoryResponseDto> paginationToDto(Pagination<Category> pagination);


}
