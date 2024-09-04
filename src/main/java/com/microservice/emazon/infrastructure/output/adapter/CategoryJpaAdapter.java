package com.microservice.emazon.infrastructure.output.adapter;

import com.microservice.emazon.domain.model.Category;
import com.microservice.emazon.domain.model.Pagination;
import com.microservice.emazon.domain.spi.ICategoryPersistencePort;
import com.microservice.emazon.domain.util.PaginationUtil;
import com.microservice.emazon.infrastructure.output.repository.ICategoryRepository;
import com.microservice.emazon.infrastructure.output.entity.CategoryEntity;
import com.microservice.emazon.infrastructure.output.mapper.ICategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/*
* Clase que implementa el puerto de persistencia de categorias
* */
@RequiredArgsConstructor
@Component
public class CategoryJpaAdapter  implements ICategoryPersistencePort {

    private final ICategoryEntityMapper categoryEntityMapper;
    private final ICategoryRepository categoryRepository;

    /*
    * Metodo que obtiene todas las categorias de la base de datos para mostrarlas
    * */
    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return Optional.empty();
    }

    /*
    * Metodo que guarda una categoria en la base de datos
    * */
    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.categoryEntityToCategory(category));
    }

    /*
    * Metodo que verifica si existe una categoria por nombre en la base de datos
    * */
    @Override
    public boolean categoryExistsByName(String categoryName) {
        return categoryRepository.existsByName(categoryName);
    }


    /*
    * Metodo que obtiene todas las categorias con paginacion y ordenamiento por nombre de forma ascendente o descendente
    * */
    @Override
    public Pagination<Category> getAllCategoriesPagination(PaginationUtil paginationUtil) {
        Sort.Direction sortDirection = paginationUtil.isAscending()? Sort.Direction.ASC : Sort.Direction.DESC; // propiedad de jpa que indica si el ordenamiento es ascendente o descendente
        PageRequest pageRequest = PageRequest.of(paginationUtil.getPageNumber(), paginationUtil.getPageSize(), Sort.by(sortDirection, paginationUtil.getNameFilter())); // objeto de jpa que contiene la informacion de la paginacion y ordenamiento
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageRequest); // consulta a la base de datos con la paginacion y ordenamiento y almacena los resultados en un objeto de tipo Page
        List<Category> categories = categoryEntityMapper.toCategoryList(categoryPage.getContent()); // mapeo de las categorias obtenidas de la base de datos a una lista de categorias de tipo Category de dominio

        // se retorna un objeto de tipo Pagination que contiene la informacion de la paginacion y ordenamiento
        return new Pagination<>(
                paginationUtil.isAscending(),
                paginationUtil.getPageNumber(),
                categoryPage.getTotalPages(),
                categoryPage.getTotalElements(),
                categories
        );
    }

    @Override
    public Set<String> getCategoryNamesByIds(Set<Long> idsCategories) {
        return categoryRepository.findAllById(idsCategories).stream() // se obtienen las categorias por medio de los ids desde la base de datos
                .map(CategoryEntity::getName) // se mapea el nombre de la categoria
                .collect(Collectors.toSet()); // se convierte el stream a un set
    }
}
