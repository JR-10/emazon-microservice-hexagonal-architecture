package com.microservice.emazon.infrastructure.output.mapper;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import com.microservice.emazon.infrastructure.output.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring", uses = {IBrandEntityMapper.class, ICategoryEntityMapper.class})
public interface IArticleEntityMapper {


    @Mapping(source = "brandId", target = "brand", qualifiedByName = "idToBrandEntity")
    @Mapping(source = "categoryIds", target = "categories", qualifiedByName = "idToCategoryEntity")
    ArticleEntity toArticleEntity(Article article);


    @Mapping(source = "brand.id", target = "brandId")
    Article toArticle(ArticleEntity articleEntity);


    List<Article> articleEntityListToArticleList(List<ArticleEntity> articleEntityList);


    @Named("idToCategoryEntity")
    default CategoryEntity idToCategoryEntity (Long categoryId) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        return categoryEntity;
    }
}
