package com.microservice.emazon.domain.model;

public class Article {

    private Long id;
    private Long idCategory;
    private Long idBrand;
    private Long quantity;
    private Long price;

    public Article(Long id, Long idCategory, Long idBrand, Long quantity, Long price) {
        this.id = id;
        this.idCategory = idCategory;
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

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
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
