package com.microservice.emazon.domain.model;

public class Article {

    private Long id;
    private String name;
    private String description;
    private Long idBrand;
    private Long quantity;
    private Long price;

    public Article(Long id, String name, String description, Long idBrand, Long quantity, Long price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idBrand = idBrand;
        this.quantity = quantity;
        this.price = price;
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

    public Long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Long idBrand) {
        this.idBrand = idBrand;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
