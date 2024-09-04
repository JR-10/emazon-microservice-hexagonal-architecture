package com.microservice.emazon.infrastructure.output.repository;

import com.microservice.emazon.infrastructure.output.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
* Interface que extiende de JpaRepository para poder realizar operaciones CRUD en la base de datos de la entidad CategoryEntity
* */
@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    // Metodo que verifica si existe una categoria por nombre en la base de datos
    boolean existsByName(String categoryName);

}
