package com.microservice.emazon.application.mapper;

import com.microservice.emazon.application.dto.ArticleRequestDto;
import com.microservice.emazon.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IArticleDtoMapper {

    Article articleDtoToArticle(ArticleRequestDto articleRequestDto);


}


