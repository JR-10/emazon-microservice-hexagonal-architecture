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
import com.microservice.emazon.infrastructure.output.mapper.IArticleEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        Pagination<Article> articlePagination = articleServicePort.getArticlesByParameters(paginationUtil); // se obtiene la paginacion de articulos de la capa de dominio
        List<Article> articlesData = articlePagination.getData(); // se obtiene la lista de articulos de la paginacion
        List<ArticleResponseDto> articlesResponses = articlesData.stream().map( // se mapea la lista de articulos de dominio a la lista de articulos de respuesta
                article -> {
                    ArticleResponseDto articleResponse = articleMapper.articleToArticleDto(article); // se mapea el articulo de dominio a articulo de respuesta
                    articleResponse.setBrand(brandMapper.brandToBrandDto(branServicePort.getBrandById(article.getBrandId()))); // se mapea la marca de dominio a marca de respuesta
                    articleResponse.setCategories(categoryMapper.categoryListToCategoryByArticleDtoList(categoryServicePort.getAllByArticle(article.getId()))); // se mapea la lista de categorias de dominio a la lista de categorias de respuesta
                    return articleResponse; // retorna el articulo de respuesta mapeado
                }
        ).toList(); // se convierte el stream a lista

        return new Pagination<>(
                articlePagination.isAscending(),
                articlePagination.getCurrentPage(),
                articlePagination.getTotalPages(),
                articlePagination.getTotalElements(),
                articlesResponses
        );

    }
}
