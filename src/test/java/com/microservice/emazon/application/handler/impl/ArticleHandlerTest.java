package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.request.ArticleRequestDto;
import com.microservice.emazon.application.mapper.IArticleDtoMapper;
import com.microservice.emazon.domain.api.IArticleServicePort;
import com.microservice.emazon.domain.model.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleHandlerTest {


    @Mock
    private IArticleServicePort articleServicePort;

    @Mock
    private IArticleDtoMapper articleMapper;

    @InjectMocks
    private ArticleHandler articleHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveArticle_savesSuccessfully() {
        ArticleRequestDto requestDto = new ArticleRequestDto();
        Article article = new Article();

        when(articleMapper.articleDtoToArticle(requestDto)).thenReturn(article);

        articleHandler.saveArticle(requestDto);

        verify(articleMapper, times(1)).articleDtoToArticle(requestDto);
        verify(articleServicePort, times(1)).saveArticle(article);
    }

}