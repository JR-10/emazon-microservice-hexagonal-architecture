package com.microservice.emazon.domain.model;

import java.util.List;

public class Article {

    private Long id;
    private String name;
    private String description;
    private Long quantity;
    private Double price;
    private Long branId;
    private List<Long> categoryIds;

    public Article() {
    }

    public Article(Long id, String name, String description, Long quantity, Double price, Long branId, List<Long> categoryIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.branId = branId;
        this.categoryIds = categoryIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getBranId() {
        return branId;
    }

    public void setBranId(Long branId) {
        this.branId = branId;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
