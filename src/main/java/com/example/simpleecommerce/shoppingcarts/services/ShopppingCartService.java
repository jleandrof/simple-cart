package com.example.simpleecommerce.shoppingcarts.services;

import com.example.simpleecommerce.products.exceptions.ProductNotFoundException;
import com.example.simpleecommerce.products.models.Product;
import com.example.simpleecommerce.products.repositories.ProductRepository;
import com.example.simpleecommerce.shoppingcarts.exceptions.ShoppingCartAlrearyExistsException;
import com.example.simpleecommerce.shoppingcarts.exceptions.ShoppingCartNotFoundException;
import com.example.simpleecommerce.shoppingcarts.models.ShoppingCart;
import com.example.simpleecommerce.shoppingcarts.models.ShoppingCartClient;
import com.example.simpleecommerce.shoppingcarts.models.ShoppingCartItem;
import com.example.simpleecommerce.shoppingcarts.models.dto.UpdateShoppingCartDTO;
import com.example.simpleecommerce.shoppingcarts.models.dto.ShoppingCartDTO;
import com.example.simpleecommerce.shoppingcarts.repositories.ShoppingCartItemRepository;
import com.example.simpleecommerce.shoppingcarts.repositories.ShoppingCartRepository;
import com.example.simpleecommerce.users.exceptions.UserNotFoundException;
import com.example.simpleecommerce.users.models.User;
import com.example.simpleecommerce.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ShopppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShopppingCartService(ShoppingCartRepository shoppingCartRepository,
                                UserRepository userRepository,
                                ProductRepository productRepository,
                                ShoppingCartItemRepository shoppingCartItemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public ShoppingCartClient onCreate(ShoppingCartDTO shoppingCartDTO)
            throws UserNotFoundException, ProductNotFoundException, ShoppingCartAlrearyExistsException {
        var shoppingCart = shoppingCartRepository.findByUser_Id(shoppingCartDTO.getUserId());
        if(shoppingCart != null)
            throw new ShoppingCartAlrearyExistsException("Usuário já possui um carrinho de compras");

        User user;
        try {
            user = userRepository.findById(shoppingCartDTO.getUserId()).get();
        } catch (Exception e) {
            throw new UserNotFoundException("Usuário não encontrado");
        }

        shoppingCart = shoppingCartRepository.save(new ShoppingCart(user));
        var itemList = new ArrayList<ShoppingCartItem>();
        for(var item : shoppingCartDTO.getItems()) {
            Product product;
            try {
                product = productRepository.findById(item.getProductId()).get();
            } catch (Exception e) {
                throw new ProductNotFoundException("Produto de id" + item.getProductId() + "não foi encontrado");
            }
            itemList.add(new ShoppingCartItem(product, item.getQuantity(), shoppingCart));
        }

        shoppingCart.setItems(shoppingCartItemRepository.saveAll(itemList));

        shoppingCart = shoppingCartRepository.save(shoppingCart);
        return shoppingCart.toClient();
    }

    @Transactional(rollbackFor = Exception.class)
    public ShoppingCart onUpdateShoppingCart(UpdateShoppingCartDTO updateShoppingCartDTO) throws ShoppingCartNotFoundException, ProductNotFoundException {
        ShoppingCart shoppingCart;
        if(updateShoppingCartDTO.getId() != null)
            try {
                shoppingCart = shoppingCartRepository.findById(updateShoppingCartDTO.getId()).get();
            } catch (Exception e) {
                throw new ShoppingCartNotFoundException("Carrinho não encontrado");
            }
        else if(updateShoppingCartDTO.getUserId() != null)
            shoppingCart = shoppingCartRepository.findByUser_Id(updateShoppingCartDTO.getUserId());
        else
            throw new ShoppingCartNotFoundException("Carrinho não encontrado");

        for(var item : updateShoppingCartDTO.getItems()) {
            Product product;
            try {
                product = productRepository.findById(item.getProductId()).get();
            } catch (Exception e) {
                throw new ProductNotFoundException("Produto não encontrado.");
            }

            switch (item.getOperation()) {
                case ADD: {
                    shoppingCart.add(new ShoppingCartItem(product, item.getQuantity(), shoppingCart));
                    break;
                }
                case REMOVE: {
                    shoppingCart.remove(product);
                    break;
                }
            }
        }
        shoppingCartItemRepository.saveAll(shoppingCart.getItems());
        return shoppingCartRepository.save(shoppingCart);
    }
}
