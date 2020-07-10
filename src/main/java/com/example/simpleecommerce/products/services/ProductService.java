package com.example.simpleecommerce.products.services;

import com.example.simpleecommerce.products.models.Product;
import com.example.simpleecommerce.products.models.ProductDTO;
import com.example.simpleecommerce.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product onCreateProduct(ProductDTO newProduct) {
        return productRepository.save(newProduct.toProduct());
    }
}
