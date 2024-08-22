package com.microservice.emazon.infrastructure.mapper;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.infrastructure.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {

    List<Category> toCategoryList(List<CategoryEntity> categoryEntityList);

    CategoryEntity toCategoryEntity(Category category);

    Category toCategory(CategoryEntity categoryEntity);
}
