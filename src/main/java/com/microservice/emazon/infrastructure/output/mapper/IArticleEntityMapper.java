package com.microservice.emazon.infrastructure.output.mapper;

import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.infrastructure.output.entity.ArticleEntity;
import com.microservice.emazon.infrastructure.output.entity.BrandEntity;
import com.microservice.emazon.infrastructure.output.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
* Interface que define los métodos para mapear un objeto de tipo ArticleEntity a un objeto de tipo Article y viceversa.
* */
@Mapper(componentModel = "spring", uses = {IBrandEntityMapper.class, ICategoryEntityMapper.class}) // se indica que se usaran los mappers de Category y Brand
public interface IArticleEntityMapper {


    /*
    * convierte un objeto de tipo Article a un objeto de tipo ArticleEntity
    * */
    @Mapping(source = "brandId", target = "brand", qualifiedByName = "idToBrandEntity") // mapea el atributo brandId de Article a brand de ArticleEntity
    @Mapping(source = "categoryIds", target = "categories", qualifiedByName = "idToCategoryEntity") // mapea el atributo categoryIds de Article a categories de ArticleEntity
    ArticleEntity toArticleEntity(Article article);


    /*
     * convierte una lista de objetos de tipo ArticleEntity a una lista de objetos de tipo Article
     * */
    @Mapping(source = "brand.id", target = "brandId")
    Article toArticle(ArticleEntity articleEntity);



    /*
    *  convierte una lista de objetos de tipo ArticleEntity a una lista de objetos de tipo Article
    * */
    List<Article> articleEntityListToArticleList(List<ArticleEntity> articleEntityList);


    // toma un id de tipo Long y lo convierte en un objeto de tipo ArticleEntity
    @Named("idToCategoryEntity")
    default CategoryEntity idToCategoryEntity (Long categoryId) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryId);
        return categoryEntity;
    }
}
