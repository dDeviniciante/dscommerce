package com.cursodevsuperior.dscommerce.dto;


import com.cursodevsuperior.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

    private long id;
    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo Requerido") // verifica se o nome n é nulo
    private String name;
    @Size(min = 10, message = "Decrição precisa ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo Requerido") // verifica se o nome n é nulo
    private String description;

    @Positive(message = "O preço deve ser positivo")
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
