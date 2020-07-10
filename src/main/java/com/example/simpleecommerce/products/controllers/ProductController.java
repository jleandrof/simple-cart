package com.example.simpleecommerce.products.controllers;

import com.example.simpleecommerce.products.models.ProductDTO;
import com.example.simpleecommerce.products.services.ProductService;
import com.example.simpleecommerce.utils.results.ErrorResult;
import com.example.simpleecommerce.utils.results.SuccessfullResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity onCreateProduct(@RequestBody ProductDTO productDTO) {
        try {
            return new ResponseEntity(new SuccessfullResult(productService.onCreateProduct(productDTO)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new ErrorResult("Erro interno"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
