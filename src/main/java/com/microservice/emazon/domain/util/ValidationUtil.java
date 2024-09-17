package com.microservice.emazon.domain.util;

import com.microservice.emazon.application.util.ApplicationConstants;
import com.microservice.emazon.domain.model.Article;
import com.microservice.emazon.domain.model.Brand;
import com.microservice.emazon.domain.model.Category;

public class ValidationUtil {

    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateCategory(Category category){

        if(category.getNameCategory() == null || category.getNameCategory().isEmpty()){
            throw new IllegalArgumentException(ApplicationConstants.CATEGORY_NAME_CANNOT_BE_EMPTY_MESSAGE);
        }
        if(category.getDescription() == null || category.getDescription().isEmpty()){
            throw new IllegalArgumentException(ApplicationConstants.CATEGORY_DESCRIPTION_CANNOT_BE_EMPTY_MESSAGE);
        }
        if(category.getNameCategory().length() > ApplicationConstants.NAME_MAX_LENGTH){
            throw new IllegalArgumentException(ApplicationConstants.NAME_CATEGORY_LENGTH_MESSAGE);
        }
        if(category.getDescription().length() > ApplicationConstants.DESCRIPTION_CATEGORY_MAX_LENGTH){
            throw new IllegalArgumentException(ApplicationConstants.DESCRIPTION_CATEGORY_LENGTH_MESSAGE);
        }
    }

    public static void validateBrand(Brand brand) {
        if (brand.getNameBrand() == null || brand.getNameBrand().isEmpty()) {
            throw new IllegalArgumentException(ApplicationConstants.BRAND_NAME_CANNOT_BE_EMPTY_MESSAGE);
        }
        if (brand.getDescription() == null || brand.getDescription().isEmpty()) {
            throw new IllegalArgumentException(ApplicationConstants.BRAND_DESCRIPTION_CANNOT_BE_EMPTY_MESSAGE);
        }
        if (brand.getNameBrand().length() >  ApplicationConstants.NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(ApplicationConstants.NAME_BRAND_LENGTH_MESSAGE);
        }
        if (brand.getDescription().length() > ApplicationConstants.DESCRIPTION_BRAND_MAX_LENGTH) {
            throw new IllegalArgumentException(ApplicationConstants.DESCRIPTION_BRAND_LENGTH_MESSAGE);
        }
    }

    public static void validateArticle(Article article) {
        if (article.getNameArticle() == null || article.getNameArticle().isEmpty()) {
            throw new IllegalArgumentException(ApplicationConstants.ARTICLE_NAME_CANNOT_BE_EMPTY_MESSAGE);
        }
        if (article.getDescription() == null || article.getDescription().isEmpty()) {
            throw new IllegalArgumentException(ApplicationConstants.ARTICLE_DESCRIPTION_CANNOT_BE_EMPTY_MESSAGE);
        }
        if (article.getNameArticle().length() >  ApplicationConstants.NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(ApplicationConstants.NAME_BRAND_LENGTH_MESSAGE);
        }
        if (article.getDescription().length() > ApplicationConstants.DESCRIPTION_ARTICLE_MAX_LENGTH) {
            throw new IllegalArgumentException(ApplicationConstants.DESCRIPTION_ARTICLE_LENGTH_MESSAGE);
        }
        if (article.getQuantity() < ApplicationConstants.VALUE_ZERO) {
            throw new IllegalArgumentException(ApplicationConstants.QUANTITY_ARTICLE_NEGATIVE_MESSAGE);
        }
        if (article.getPrice() < ApplicationConstants.VALUE_ZERO) {
            throw new IllegalArgumentException(ApplicationConstants.PRICE_ARTICLE_NEGATIVE_MESSAGE);
        }
    }
}