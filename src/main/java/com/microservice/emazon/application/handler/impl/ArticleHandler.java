package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.ArticleRequestDto;
import com.microservice.emazon.application.handler.IArticleHandler;
import com.microservice.emazon.application.mapper.IArticleDtoMapper;
import com.microservice.emazon.domain.api.IArticleServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler {

    private final IArticleServicePort articleServicePort;
    private final IArticleDtoMapper articleMapper;

    @Override
    public void saveArticle(ArticleRequestDto articleRequestDto) {
        articleServicePort.saveArticle(articleMapper.articleDtoToArticle(articleRequestDto));
    }
}
