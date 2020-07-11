package com.example.simpleecommerce.shoppingcarts.models;

import com.example.simpleecommerce.products.models.Product;
import com.example.simpleecommerce.users.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shopping_carts")
@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue
    private Long id;

    private String uuid;

    @JsonIgnore
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "shoppingCart")
    private List<ShoppingCartItem> items;

    public ShoppingCart(User user) {
        this.uuid = UUID.randomUUID().toString();
        this.user = user;
        this.items = new ArrayList<>();
    }

    @JsonIgnore
    public Long calculateTotalPrice() {
        Long totalPrice = 0L;
        for(var item : items) {
            totalPrice += (item.getProduct().getPrice() * item.getQuantity());
        }

        return totalPrice;
    }

    public void add(ShoppingCartItem shoppingCartItem) {
        items.add(shoppingCartItem);
    }

    public void remove(Product product) {
        items.removeIf(it -> it.getProduct().equals(product));
    }

    public void emptyCart() {
        items.clear();
    }

    public ShoppingCartClient toClient() {
        var itemClientList = new ArrayList<ShoppingCartItemClient>();
        System.out.println(items);
        for(var item : items) {
            itemClientList.add(item.toClient());
            System.out.println(item);
        }

        return new ShoppingCartClient(id, uuid, user.getId(), itemClientList);
    }
}
