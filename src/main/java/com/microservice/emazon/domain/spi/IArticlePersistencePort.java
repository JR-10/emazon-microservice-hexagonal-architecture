package com.microservice.emazon.domain.spi;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;

public interface IArticlePersistencePort {

    void saveArticle(Article article);

    boolean articleExistsByNameArticle(String articleName);

    Pagination<Article> getArticlesByParameters(PaginationUtil paginationUtil);
}
