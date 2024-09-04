package com.microservice.emazon.application.handler;

import com.microservice.emazon.application.dto.request.ArticleRequestDto;

public interface IArticleHandler {

    void saveArticle(ArticleRequestDto articleRequestDto);
}
