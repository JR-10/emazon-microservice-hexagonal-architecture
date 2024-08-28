package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.api.IArticleServicePort;
import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("Article cannot be null");
        }
        articlePersistencePort.saveArticle(article);
    }
}
