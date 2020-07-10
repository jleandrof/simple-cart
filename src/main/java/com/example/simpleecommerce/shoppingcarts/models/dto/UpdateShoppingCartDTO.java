package com.example.simpleecommerce.shoppingcarts.models.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class UpdateShoppingCartDTO {
    private Long id;
    private Long userId;
    private List<UpdateShoppingCartItemDTO> items;
}
