package com.microservice.emazon.domain.api;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;

public interface IArticleServicePort {

    void saveArticle(Article article);

    Pagination<Article> getArticlesByParameters(PaginationUtil paginationUtil);
}
