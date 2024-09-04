package com.microservice.emazon.application.mapper;

import com.microservice.emazon.application.dto.request.CategoryRequestDto;
import com.microservice.emazon.application.dto.response.CategoryResponseDto;
import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


/*
 * Interface para hacer el mapeo de las categorias a DTOs y viceversa
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
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


    /*
     * Convert Pagination<Category> to Pagination<CategoryResponseDto>
     * @param pagination
     * @return Pagination<CategoryResponseDto>
     */
    Pagination<CategoryResponseDto> paginationToCategoryResponseDto(Pagination<Category> pagination);


}
