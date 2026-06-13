package com.example.shivamart.service.impl;

import com.example.shivamart.entity.Cart;
import com.example.shivamart.entity.CartItem;
import com.example.shivamart.entity.Product;
import com.example.shivamart.entity.User;
import com.example.shivamart.repository.CartItemRepository;
import com.example.shivamart.repository.CartRepository;
import com.example.shivamart.repository.ProductRepository;
import com.example.shivamart.repository.UserRepository;
import com.example.shivamart.service.CartService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart getCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    @Override
    public Cart addToCart(
            Long userId,
            Long productId,
            Integer quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        CartItem existingItem = cart.getItems()
                .stream()
                .filter(item ->
                        item.getProduct()
                                .getId()
                                .equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {

            existingItem.setQuantity(
                    existingItem.getQuantity() + quantity);

            cartItemRepository.save(existingItem);

        } else {

            CartItem cartItem = new CartItem();

            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);

            cartItemRepository.save(cartItem);

            cart.getItems().add(cartItem);
        }

        return cartRepository.findById(cart.getId())
                .orElseThrow(() ->
                        new RuntimeException("Cart not found"));
    }

    @Override
    public void removeFromCart(Long cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException("Cart Item not found"));

        cartItemRepository.delete(cartItem);
    }

    @Override
    public void clearCart(Long userId) {

        Cart cart = getCart(userId);

        cartItemRepository.deleteAll(cart.getItems());

        cart.getItems().clear();

        cartRepository.save(cart);
    }
}