package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.ArticleRequestDto;

public interface IArticleHandler {

    void saveArticle(ArticleRequestDto articleRequestDto);
}
