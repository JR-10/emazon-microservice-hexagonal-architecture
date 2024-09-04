package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.infrastructure.output.mapper.IArticleEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.IArticleRepository;
import com.microservice.emazon.infrastructure.output.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {

    private final IArticleEntityMapper articleEntityMapper;
    private final IArticleRepository articleRepository;
    private final IBrandRepository brandRepository;

    @Override
    public void saveArticle(Article article) {
        articleRepository.save(articleEntityMapper.toArticleEntity(article));
    }

    @Override
    public boolean articleExistsByName(String articleName) {
        return articleRepository.existsByName(articleName);
    }
}
