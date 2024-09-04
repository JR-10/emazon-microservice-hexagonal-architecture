package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.application.util.ApplicationConstants;
import com.microservice.emazon.domain.api.IArticleServicePort;
import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.domain.exeptions.ArticleException;
import com.microservice.emazon.domain.util.ValidationUtil;


public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        ValidationUtil.validateArticle(article);
        if (articlePersistencePort.articleExistsByName(article.getName())) {
            throw new ArticleException.ArticleNameAlreadyExistsException(ApplicationConstants.ARTICLE_NAME_ALREADY_EXISTS_MESSAGE);
        }
        articlePersistencePort.saveArticle(article);
    }
}