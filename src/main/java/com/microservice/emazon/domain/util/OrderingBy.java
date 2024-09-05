package com.microservice.emazon.domain.util;

public enum OrderingBy {
    ARTICLE_NAME("articleName"),
    BRAND_NAME("brandName"),
    NUMBER_OF_CATEGORIES("numberOfCategories");

    private final String fieldName;

    OrderingBy(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
