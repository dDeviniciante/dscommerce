package com.cursodevsuperior.dscommerce.dto;


import com.cursodevsuperior.dscommerce.entities.Product;

public class ProductDTO {

    private long id;
    private String name;
    private String description;
    private double price;
    private String imgUrl;

    public ProductDTO() {}

    public ProductDTO(long id, String imgUrl, String name, String description, double price) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductDTO(Product entity) {
        id = entity.getId();
        imgUrl = entity.getImgUrl();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
