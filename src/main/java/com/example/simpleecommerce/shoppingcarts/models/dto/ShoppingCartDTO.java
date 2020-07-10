package com.example.simpleecommerce.shoppingcarts.models.dto;

import lombok.Getter;

import java.util.HashSet;

@Getter
public class ShoppingCartDTO {
    private Long userId;

    private HashSet<ShoppingCartItemDTO> items;
}
