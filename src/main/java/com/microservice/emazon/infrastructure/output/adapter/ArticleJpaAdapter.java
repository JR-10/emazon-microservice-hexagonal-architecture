package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.spi.IArticlePersistencePort;
import com.microservice.emazon.infrastructure.exeptions.ArticleException;
import com.microservice.emazon.infrastructure.output.entity.BrandEntity;
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
        if(articleRepository.existsByNameIgnoreCase(article.getName())) {
            throw new ArticleException("Article already exists");
        }
        try {
            BrandEntity brandEntity = brandRepository.findById(article.getIdBrand()).orElseThrow(() -> new ArticleException("Brand not found"));
            articleRepository.save(articleEntityMapper.articleToArticleEntity(article, brandEntity));
        } catch (Exception e) {
            throw new ArticleException(e.getMessage());
        }

    }
}
