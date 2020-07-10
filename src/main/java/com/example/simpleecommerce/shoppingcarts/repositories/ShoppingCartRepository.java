package com.example.simpleecommerce.shoppingcarts.repositories;

import com.example.simpleecommerce.shoppingcarts.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUser_Id(Long id);
}
