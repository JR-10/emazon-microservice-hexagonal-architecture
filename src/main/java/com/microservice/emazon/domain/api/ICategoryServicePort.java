package com.microservice.emazon.domain.api;

import com.microservice.emazon.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/*
 * This interface is used to define the methods that will be used to interact with the Category entity.
 * Permite comunicarnos con la tecnologia externa propia del lenguaje, como se implemento JPA para comunicar con la BD
 */
public interface ICategoryServicePort {

    List<Category> getAllCategories();

    Page<Category> getCategories(String order, Pageable pageable);

    Optional<Category> getCategory(Long id);

    void saveCategory(Category category);

    void deleteCategory(Long id);

    // TODO: modificacion 4 - Se agrega el metodo getPagination a la interfaz ICategoryServicePort
    List<Category> getPagination(Pageable pageable);

}
