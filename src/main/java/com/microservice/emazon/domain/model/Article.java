package com.microservice.emazon.domain.model;

import java.util.List;
import java.util.Set;

public class Article {

    private Long id;
    private String nameArticle;
    private String description;
    private Long quantity;
    private Double price;
    private Long brandId;
    private List<Long> categoryIds;

    public Article() {
    }

    public Article(Long id, String nameArticle, String description, Long quantity, Double price, Long brandId, List<Long> categoryIds) {
        this.id = id;
        this.nameArticle = nameArticle;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brandId = brandId;
        this.categoryIds = categoryIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }


    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}