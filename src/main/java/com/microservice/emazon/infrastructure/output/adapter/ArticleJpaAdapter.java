package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.infrastructure.output.mapper.IArticleEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleEntityMapper articleEntityMapper;
    private final IArticleRepository articleRepository;

    @Override
    public void saveArticle(Article article) {
        if(articleRepository.existsByNameIgnoreCase(article.getName())) {
            throw new IllegalArgumentException("Article already exists");
        }
        articleRepository.save(articleEntityMapper.toArticleEntity(article));

    }
}
