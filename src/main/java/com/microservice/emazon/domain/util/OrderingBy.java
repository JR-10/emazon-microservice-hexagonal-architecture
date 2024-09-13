package com.microservice.emazon.domain.util;

public enum OrderingBy {
    ARTICLE_NAME("nameArticle"),
    BRAND_NAME("nameBrand"),
    NUMBER_OF_CATEGORIES("numCategories");

    private final String fieldName;

    OrderingBy(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
