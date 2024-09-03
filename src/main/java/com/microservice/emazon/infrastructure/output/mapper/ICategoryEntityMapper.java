package com.microservice.emazon.infrastructure.output.mapper;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.infrastructure.output.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {

    List<Category> toCategoryList(List<CategoryEntity> categoryEntityList);

    CategoryEntity categoryEntityToCategory(Category category);

    Category categoryToCategoryEntity(CategoryEntity categoryEntity);
}
