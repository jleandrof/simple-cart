package com.example.simpleecommerce.shoppingcarts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShoppingCartItemClient {
    private Long productId;
    private Integer quantity;

}
