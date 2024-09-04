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
@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    uses = {ICategoryEntityMapper.class, IBrandEntityMapper.class}) // se indica que se usaran los mappers de Category y Brand
public interface IArticleEntityMapper {

    /*
    * convierte una lista de objetos de tipo ArticleEntity a una lista de objetos de tipo Article
    * */
    Article toArticle(ArticleEntity articleEntity);


    /*
    * convierte un objeto de tipo Article a un objeto de tipo ArticleEntity
    * */
    @Mapping(source = "brandId", target = "brand", qualifiedByName = "mapBrandIdToBrandEntity") // mapea el atributo brandId de Article a brand de ArticleEntity
    @Mapping(source = "categoryIds", target = "categories", qualifiedByName = "mapCategoryIdsToCategoryEntities") // mapea el atributo categoryIds de Article a categories de ArticleEntity
    ArticleEntity toArticleEntity(Article article);

    /*
    * convierte un id de marca a un objeto de tipo BrandEntity
    * */
    @Named("mapBrandIdToBrandEntity") // define un nombre para el metodo
    default BrandEntity mapBrandIdToBrandEntity(Long brandId) {
        if (brandId == null) {
            return null;
        }
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setId(brandId);
        return brandEntity;
    }

    /*
    * realiza el mapeo de una lista de ids de categorias a una lista de objetos de tipo CategoryEntity
    * */
    @Named("mapCategoryIdsToCategoryEntities") // define un nombre para el metodo
    default Set<CategoryEntity> mapCategoryIdsToCategoryEntities(Set<Long> categoryIds) {
        if (categoryIds == null) {
            return null;
        }
        return categoryIds.stream().map(id -> {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(id);
            return categoryEntity;
        }).collect(Collectors.toSet());
    }


    /*
    *  convierte una lista de objetos de tipo ArticleEntity a una lista de objetos de tipo Article
    * */
    List<Article> articleEntityListToArticleList(List<ArticleEntity> articleEntityList);
}
