package com.microservice.emazon.domain.spi;

import com.microservice.emazon.domain.model.Article;

public interface IArticlePersistencePort {

    void saveArticle(Article article);

    boolean articleExistsByName(String articleName);
}
