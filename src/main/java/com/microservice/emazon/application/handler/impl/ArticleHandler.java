package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.request.ArticleRequestDto;
import com.microservice.emazon.application.dto.response.ArticleResponseDto;
import com.microservice.emazon.application.handler.IArticleHandler;
import com.microservice.emazon.application.mapper.IArticleDtoMapper;
import com.microservice.emazon.application.mapper.IBrandDtoMapper;
import com.microservice.emazon.application.mapper.ICategoryDtoMapper;
import com.microservice.emazon.domain.api.IArticleServicePort;
import com.microservice.emazon.domain.api.IBrandServicePort;
import com.microservice.emazon.domain.api.ICategoryServicePort;
import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final IArticleDtoMapper articleMapper;

    private final IBrandServicePort branServicePort;
    private final IBrandDtoMapper brandMapper;


    private final ICategoryServicePort categoryServicePort;
    private final ICategoryDtoMapper categoryMapper;


    @Override
    public void saveArticle(ArticleRequestDto articleRequestDto) {
        Article article = articleMapper.articleDtoToArticle(articleRequestDto);
        articleServicePort.saveArticle(article);
    }

    @Override
    public Pagination<ArticleResponseDto> getArticlesByParameters(PaginationUtil paginationUtil) {

        Pagination<Article> articlePagination = articleServicePort.getArticlesByParameters(paginationUtil);
        List<Article> articlesData = articlePagination.getData();
        List<ArticleResponseDto> articlesResponses = articlesData.stream().map(
                article -> {
                    ArticleResponseDto articleResponse = articleMapper.articleToArticleDto(article);
                    articleResponse.setBrand(brandMapper.brandToBrandDto(branServicePort.getBrandById(article.getBrandId())));
                    articleResponse.setCategories(categoryMapper.categoryListToCategoryByArticleDtoList(categoryServicePort.getAllByArticle(article.getId())));
                    return articleResponse;
                }
        ).toList();

        return new Pagination<>(
                articlePagination.isAscending(),
                articlePagination.getCurrentPage(),
                articlePagination.getTotalPages(),
                articlePagination.getTotalElements(),
                articlesResponses
        );

    }
}
