package com.example.simpleecommerce.shoppingcarts.controllers;

import com.example.simpleecommerce.products.models.ProductDTO;
import com.example.simpleecommerce.shoppingcarts.models.dto.ShoppingCartDTO;
import com.example.simpleecommerce.shoppingcarts.models.dto.UpdateShoppingCartDTO;
import com.example.simpleecommerce.shoppingcarts.services.ShopppingCartService;
import com.example.simpleecommerce.utils.results.ErrorResult;
import com.example.simpleecommerce.utils.results.SuccessfullResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/carts")
public class ShoppingCartController {
    private final ShopppingCartService shopppingCartService;

    @Autowired
    public ShoppingCartController(ShopppingCartService shopppingCartService) {
        this.shopppingCartService = shopppingCartService;
    }

    @PostMapping
    public ResponseEntity onCreateProduct(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        try {
            return new ResponseEntity(new SuccessfullResult(shopppingCartService.onCreate(shoppingCartDTO)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResult("Erro interno"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping
    public ResponseEntity onCreateProduct(@RequestBody UpdateShoppingCartDTO updateShoppingCartDTO) {
        try {
            return new ResponseEntity(new SuccessfullResult(shopppingCartService.onUpdateShoppingCart(updateShoppingCartDTO)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResult("Erro interno"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
