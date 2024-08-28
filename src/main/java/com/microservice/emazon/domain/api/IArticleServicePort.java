package com.microservice.emazon.domain.api;

import com.microservice.emazon.domain.model.Article;

public interface IArticleServicePort {

    void saveArticle(Article article);
}
