package com.microservice.emazon.domain.usecase;

import com.microservice.emazon.application.util.ApplicationConstants;
import com.microservice.emazon.domain.api.IArticleServicePort;
import com.microservice.emazon.domain.exceptions.BrandExceptions;
import com.microservice.emazon.domain.exceptions.CategoryExceptions;
import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.domain.exceptions.ArticleException;
import com.microservice.emazon.domain.spi.IBrandPersistencePort;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.domain.util.ValidationUtil;

import java.util.List;


public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;
    private final IBrandPersistencePort brandPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort, IBrandPersistencePort brandPersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.brandPersistencePort = brandPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        ValidationUtil.validateArticle(article);
        if (articlePersistencePort.articleExistsByNameArticle(article.getNameArticle())) {
            throw new ArticleException.ArticleNameAlreadyExistsException(ApplicationConstants.ARTICLE_NAME_ALREADY_EXISTS_MESSAGE);
        }

        Brand brand = brandPersistencePort.getBrandById(article.getBrandId());
        if (brand == null) {
            throw new BrandExceptions.BrandNotFoundException(ApplicationConstants.BRAND_NOT_FOUND_MESSAGE);
        }

        List<Long> categories = article.getCategoryIds();

        List<String> categoryNames = categoryPersistencePort.getCategoryNamesByIds(categories);

        if (categoryNames.isEmpty()) {
            throw new CategoryExceptions.CategoryNotFoundException(ApplicationConstants.CATEGORY_NOT_FOUND_MESSAGE);
        }

        if(categoryNames.size() != categories.size()) {
            throw new CategoryExceptions.CategoryNotFoundException(ApplicationConstants.CATEGORY_NOT_FOUND_MESSAGE);
        }

        articlePersistencePort.saveArticle(article);
    }

    @Override
    public Pagination<Article> getArticlesByParameters(PaginationUtil paginationUtil) {
        return articlePersistencePort.getArticlesByParameters(paginationUtil);
    }
}