package com.example.simpleecommerce.shoppingcarts.repositories;

import com.example.simpleecommerce.shoppingcarts.models.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
}
