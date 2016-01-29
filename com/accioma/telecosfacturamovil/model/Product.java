package com.accioma.telecosfacturamovil.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table PRODUCT.
 */
public class Product {

    private Long id;
    private String name;
    private String description;
    private Float standard_price;
    private Float vat_tax;

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, String name, String description, Float standard_price, Float vat_tax) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.standard_price = standard_price;
        this.vat_tax = vat_tax;
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

    public Float getStandard_price() {
        return standard_price;
    }

    public void setStandard_price(Float standard_price) {
        this.standard_price = standard_price;
    }

    public Float getVat_tax() {
        return vat_tax;
    }

    public void setVat_tax(Float vat_tax) {
        this.vat_tax = vat_tax;
    }

}