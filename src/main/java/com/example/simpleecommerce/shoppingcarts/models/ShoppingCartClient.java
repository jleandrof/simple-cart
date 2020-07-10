package com.example.simpleecommerce.shoppingcarts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ShoppingCartClient {
    private Long id;
    private String uuid;
    private Long userId;
    private List<ShoppingCartItemClient> items;
}
