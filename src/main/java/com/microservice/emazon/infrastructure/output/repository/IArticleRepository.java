package com.microservice.emazon.infrastructure.output.repository;

import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {

    boolean existsByNameIgnoreCase(String name);

}
