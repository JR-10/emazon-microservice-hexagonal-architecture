package com.microservice.emazon.application.mapper;

import com.microservice.emazon.application.dto.request.ArticleRequestDto;
import com.microservice.emazon.application.dto.response.ArticleResponseDto;
import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IArticleDtoMapper {

    Article articleDtoToArticle(ArticleRequestDto articleRequestDto);

    Pagination<ArticleResponseDto> paginationArticleToArticleResponseDto(Pagination<Article> pagination);

}

