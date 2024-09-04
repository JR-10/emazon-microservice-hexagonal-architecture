package com.microservice.emazon.domain.spi;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/*
* SPI - Service Provider Interface, definida como puerta de salida para interactuar con tecnologias externas,
* Interfaz que se utiliza como puerta de salida para interactuar con tecnologias externas y define los métodos
* que se utilizarán para interactuar con la entidad Category por medio de la persistencia de datos
* */
public interface ICategoryPersistencePort {

    List<Category> getAllCategories();

    Optional<Category> getCategory(Long id);

    void saveCategory(Category category);

    boolean categoryExistsByName (String categoryName);

    Pagination<Category> getAllCategoriesPagination(PaginationUtil paginationUtil);

    Set<String> getCategoryNamesByIds(Set<Long> ids);
}
