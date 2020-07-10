package com.example.simpleecommerce.shoppingcarts.models.dto;

import com.example.simpleecommerce.shoppingcarts.models.ShoppingCartOperations;
import lombok.Getter;

@Getter
public class UpdateShoppingCartItemDTO {
    private ShoppingCartOperations operation;
    private Long productId;
    private Integer quantity;
}
