package com.microservice.emazon.infrastructure.output.mapper;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import com.microservice.emazon.infrastructure.output.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IArticleEntityMapper {

    @Mapping(source = "article.name", target = "name")
    @Mapping(source = "article.id", target = "id")
    @Mapping(source = "article.description", target = "description")
    @Mapping(source = "brandEntity", target = "brand")
    ArticleEntity articleToArticleEntity(Article article, BrandEntity brandEntity);

    Article toArticle(ArticleEntity articleEntity);

}
