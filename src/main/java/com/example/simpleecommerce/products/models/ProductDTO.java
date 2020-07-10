package com.example.simpleecommerce.products.models;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ProductDTO {
    @NotNull(message = "O nome não pode ser nulo")
    private String name;

    private String description = "";

    @NotNull(message = "O preço não pode ser nulo")
    private Long price;

    private Boolean visible = true;

    public Product toProduct() {
        return new Product(name, description, price, visible);
    }
}
