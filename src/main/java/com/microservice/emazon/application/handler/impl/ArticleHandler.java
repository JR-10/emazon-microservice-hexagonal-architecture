package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.request.ArticleRequestDto;
import com.microservice.emazon.application.dto.response.ArticleResponseDto;
import com.microservice.emazon.application.handler.IArticleHandler;
import com.microservice.emazon.application.mapper.IArticleDtoMapper;
import com.microservice.emazon.domain.api.IArticleServicePort;
import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final IArticleDtoMapper articleMapper;

    @Override
    public void saveArticle(ArticleRequestDto articleRequestDto) {
        Article article = articleMapper.articleDtoToArticle(articleRequestDto);
        articleServicePort.saveArticle(article);
    }

    @Override
    public Pagination<ArticleResponseDto> getArticlesByParameters(PaginationUtil paginationUtil) {
        Pagination<Article> articles = articleServicePort.getArticlesByParameters(paginationUtil);
        return articleMapper.paginationArticleToArticleResponseDto(articles);
    }
}
