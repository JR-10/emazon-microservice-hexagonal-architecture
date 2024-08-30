package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.domain.api.IArticleServicePort;
import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.infrastructure.exeptions.ArticleException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        if(article.getBranId() == null){
            throw new ArticleException("Brand cannot be null");
        }
        articlePersistencePort.saveArticle(article);
    }
}
