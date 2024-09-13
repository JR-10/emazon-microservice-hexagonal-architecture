package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import com.microservice.emazon.infrastructure.output.mapper.IArticleEntityMapper;
import com.microservice.emazon.infrastructure.output.repository.IArticleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleJpaAdapterTest {


    @Mock
    private  IArticleEntityMapper articleEntityMapper;

    @Mock
    private  IArticleRepository articleRepository;

    @InjectMocks
    private ArticleJpaAdapter articleJpaAdapter;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveArticleSuccessfully() {
        Article article = new Article(1L, "ArticleName", "ArticleDescription", 1L, 10.0, 1L, List.of(1L));
        ArticleEntity articleEntity = new ArticleEntity(1L, "ArticleName", "ArticleDescription", 1L, 1L, null, null);

        when(articleEntityMapper.toArticleEntity(article)).thenReturn(articleEntity);

        articleJpaAdapter.saveArticle(article);

        verify(articleRepository, times(1)).save(articleEntity);
    }



    @Test
    void articleExistsByNameArticle_ReturnsTrueWhenArticleExists() {
        String articleName = "ExistingArticle";
        when(articleRepository.existsByNameArticle(articleName)).thenReturn(true);

        boolean result = articleJpaAdapter.articleExistsByNameArticle(articleName);

        assertTrue(result);
    }

    @Test
    void articleExistsByNameArticle_ReturnsFalseWhenArticleDoesNotExist() {
        String articleName = "NonExistingArticle";
        when(articleRepository.existsByNameArticle(articleName)).thenReturn(false);

        boolean result = articleJpaAdapter.articleExistsByNameArticle(articleName);

        assertFalse(result);
    }
}