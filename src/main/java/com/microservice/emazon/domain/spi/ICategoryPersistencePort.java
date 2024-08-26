package com.microservice.emazon.domain.spi;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;

import java.util.List;
import java.util.Optional;

/*
 * SPI - Service Provider Interface
 * Category persistence port
 * Inteferfaz definida como puerta de salida para interactuar con tecnologias externas
 * Las implementaciones concretas de estas interfaces se inyectan desde fuera del núcleo (Infraestructura)
 */
public interface ICategoryPersistencePort {

    List<Category> getAllCategories();

    Optional<Category> getCategory(Long id);

    void saveCategory(Category category);

    // TODO: modificacion 6 - Se agrega el metodo getPagination a la interfaz ICategoryPersistencePort
    Pagination<Category> getPagination(PaginationUtil paginationUtil);
}
