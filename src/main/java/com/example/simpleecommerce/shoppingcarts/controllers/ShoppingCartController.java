package com.example.simpleecommerce.shoppingcarts.controllers;

import com.example.simpleecommerce.auth.AuthService;
import com.example.simpleecommerce.products.models.ProductDTO;
import com.example.simpleecommerce.shoppingcarts.models.ShoppingCartItem;
import com.example.simpleecommerce.shoppingcarts.models.dto.ShoppingCartDTO;
import com.example.simpleecommerce.shoppingcarts.models.dto.ShoppingCartItemDTO;
import com.example.simpleecommerce.shoppingcarts.models.dto.UpdateShoppingCartDTO;
import com.example.simpleecommerce.shoppingcarts.services.ShopppingCartService;
import com.example.simpleecommerce.utils.results.ErrorResult;
import com.example.simpleecommerce.utils.results.SuccessfullResult;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/v1/carts")
public class ShoppingCartController {
    private final ShopppingCartService shopppingCartService;
    private final AuthService authService = new AuthService();

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

    @PatchMapping("/add")
    public ResponseEntity onAddProduct(@RequestHeader("Authorization") String authorization,
                                       @RequestBody ShoppingCartItemDTO shoppingCartItemDTO) {
        Long userId;
        try {
            if(!authService.verifyUserToken(authorization))
                return new ResponseEntity(new ErrorResult("Problema na authenticação"), HttpStatus.UNAUTHORIZED);

            userId = (Long) authService.getTokenClaims(authorization).getClaim("user_id");
        } catch (Exception e) {
            return new ResponseEntity(new ErrorResult("Problema na authenticação"), HttpStatus.UNAUTHORIZED);
        }

        try {
            return new ResponseEntity(new SuccessfullResult(shopppingCartService.onAddProductToCart(userId, shoppingCartItemDTO)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResult("Erro interno"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
