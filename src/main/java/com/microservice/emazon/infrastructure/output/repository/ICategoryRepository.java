package com.microservice.emazon.infrastructure.output.repository;

import com.microservice.emazon.infrastructure.output.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/*
* Interface que extiende de JpaRepository para poder realizar operaciones CRUD en la base de datos de la entidad CategoryEntity
* */
@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    // Metodo que verifica si existe una categoria por nombre en la base de datos
    Optional<CategoryEntity> findByNameCategory(String name);


    @Query("SELECT c FROM CategoryEntity c JOIN c.articles p WHERE p.id = :articleId ORDER BY c.nameCategory ASC")
    List<CategoryEntity> findCategoriesByArticleId(@Param("articleId") Long articleId);

}
