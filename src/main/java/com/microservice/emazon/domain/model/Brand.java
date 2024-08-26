// TODO: 1 - Create a Brand class with nameBrand and descriptionBrand attributes
package com.microservice.emazon.domain.model;

public class Brand {

    private String nameBrand;
    private String descriptionBrand;

    public Brand(String nameBrand, String descriptionBrand) {
        this.nameBrand = nameBrand;
        this.descriptionBrand = descriptionBrand;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public String getDescriptionBrand() {
        return descriptionBrand;
    }

    public void setDescriptionBrand(String descriptionBrand) {
        this.descriptionBrand = descriptionBrand;
    }
}
