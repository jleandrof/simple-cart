package com.example.simpleecommerce.shoppingcarts.models;

import com.example.simpleecommerce.products.models.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "shopping_cart_items")
@NoArgsConstructor
public class ShoppingCartItem {
    @Id
    @GeneratedValue()
    private Long Id = 0L;

    private String uuid;

    @OneToOne
    private Product product;

    @JsonIgnore
    @ManyToOne
    private ShoppingCart shoppingCart;

    private Integer quantity;

    public ShoppingCartItem(Product product, Integer quantity, ShoppingCart shoppingCart) {
        this.uuid = UUID.randomUUID().toString();
        this.product = product;
        this.quantity = quantity;
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCartItemClient toClient() {
        return new ShoppingCartItemClient(product.getId(), quantity);
    }
}
