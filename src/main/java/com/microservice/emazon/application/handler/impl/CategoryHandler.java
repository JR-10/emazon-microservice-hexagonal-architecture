package com.microservice.emazon.application.handler.impl;

import com.microservice.emazon.application.dto.request.CategoryRequestDto;
import com.microservice.emazon.application.dto.response.CategoryResponseDto;
import com.microservice.emazon.application.handler.ICategoryHandler;
import com.microservice.emazon.application.mapper.ICategoryDtoMapper;
import com.microservice.emazon.domain.api.ICategoryServicePort;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


/*
* Clase para manejar las operaciones de categoría en la capa de aplicación
* */
@Service
@RequiredArgsConstructor
public class CategoryHandler implements ICategoryHandler {

    // inyectamos el puerto de servicio de categoría y el mapeador de DTO de categoría
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryDtoMapper categoryMapper;


    /*
    * Metodo para obtener todas las categorías desde el puerto de servicio de categoría
    * */
    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryMapper.categoryListToCategoryDtoList(categoryServicePort.getAllCategories());
    }


    /*
    * Metodo para guardar una categoría en el puerto de servicio de categoría
    * */
    @Override
    public void saveCategory(CategoryRequestDto categoryRequestDto) {
        categoryServicePort.saveCategory(categoryMapper.categoryDtoToCategory(categoryRequestDto));
    }


    /*
    * Metodo para obtener todas las categorías paginadas desde el puerto de servicio de categoría con la ayuda de un objeto de paginación de utilidad
    * */
    @Override
    public Pagination<CategoryResponseDto> getAllCategoriesPagination(PaginationUtil paginationUtil) {
        return categoryMapper.paginationToCategoryResponseDto(categoryServicePort.getAllCategoriesPagination(paginationUtil));
    }

}
