package com.microservice.emazon.infrastructure.output.mapper;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IArticleEntityMapper {

    ArticleEntity toArticleEntity(Article article);

}
