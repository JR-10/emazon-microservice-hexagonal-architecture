package com.microservice.emazon.infrastructure.output.repository;

import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {

    boolean existsByNameArticle(String name);


    @Query("SELECT a FROM ArticleEntity a ORDER BY a.nameArticle ASC ")
    Page<ArticleEntity> findAllOrderingByArticleNameAsc(Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a ORDER BY a.nameArticle DESC")
    Page<ArticleEntity> findAllOrderingByArticleNameDesc(Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a JOIN a.brand b ORDER BY b.nameBrand ASC")
    Page<ArticleEntity> findAllOrderingByBrandNameAsc(Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a JOIN a.brand b ORDER BY b.nameBrand DESC")
    Page<ArticleEntity> findAllOrderingByBrandNameDesc(Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a LEFT JOIN a.categories c GROUP BY a.id ORDER BY COUNT(c.id) ASC, MIN(c.nameCategory) ASC")
    Page<ArticleEntity> findAllOrderingByNumberOfCategoriesAsc(Pageable pageable);

    @Query("SELECT a FROM ArticleEntity a LEFT JOIN a.categories c GROUP BY a.id ORDER BY COUNT(c.id) DESC, MIN(c.nameCategory) ASC")
    Page<ArticleEntity> findAllOrderingByNumberOfCategoriesDesc(Pageable pageable);
}
