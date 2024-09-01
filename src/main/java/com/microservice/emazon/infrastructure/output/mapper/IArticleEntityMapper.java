package com.microservice.emazon.infrastructure.output.mapper;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import com.microservice.emazon.infrastructure.output.entity.BrandEntity;
import com.microservice.emazon.infrastructure.output.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    uses = {ICategoryEntityMapper.class, IBrandEntityMapper.class})
public interface IArticleEntityMapper {

    Article toArticle(ArticleEntity articleEntity);


    @Mapping(source = "brandId", target = "brand", qualifiedByName = "mapBrandIdToBrandEntity")
    @Mapping(source = "categoryIds", target = "categories", qualifiedByName = "mapCategoryIdsToCategoryEntities")
    ArticleEntity toArticleEntity(Article article);

    @Named("mapBrandIdToBrandEntity")
    default BrandEntity mapBrandIdToBrandEntity(Long brandId) {
        if (brandId == null) {
            return null;
        }
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brandId);
        return brandEntity;
    }

    @Named("mapCategoryIdsToCategoryEntities")
    default Set<CategoryEntity> mapCategoryIdsToCategoryEntities(Set<Long> categoryIds) {
        if (categoryIds == null) {
            return null;
        }
        return categoryIds.stream().map(id -> {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(id);
            return categoryEntity;
        }).collect(Collectors.toSet());
    }
}
