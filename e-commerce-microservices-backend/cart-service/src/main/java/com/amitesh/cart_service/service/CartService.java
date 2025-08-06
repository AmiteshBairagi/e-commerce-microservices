package com.amitesh.cart_service.service;

import com.amitesh.cart_service.FeignClient.ProductClient;
import com.amitesh.cart_service.dto.CartItemDto;
import com.amitesh.cart_service.dto.ProductDto;
import com.amitesh.cart_service.model.Cart;
import com.amitesh.cart_service.model.CartItem;
import com.amitesh.cart_service.repository.CartItemRepository;
import com.amitesh.cart_service.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {

    private final ProductClient productClient;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(ProductClient productClient, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.productClient = productClient;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }


    public Cart addToCart(CartItemDto cartItemDto) {
        int userId = cartItemDto.getUserId();
        int productId = cartItemDto.getProductId();
        int quantity = cartItemDto.getQuantity();

        ProductDto productDto = productClient.getProductDtoById(productId).getBody();
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> {
            Cart newCart = new Cart( userId, new ArrayList<>());
            return cartRepository.save(newCart);  // 🔹 Ensures the cart gets an ID in DB
        });

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProductId() == (productId))
                .findFirst()
                .orElse(null);

        if(existingItem != null){
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        }else{
            CartItem cartItem = new CartItem(cart,productId,quantity,productDto.getPrice());
            cart.getItems().add(cartItem);
        }

        System.out.println("Cart ID before saving: " + cart.getId());

        return cartRepository.save(cart);

    }

    public Cart updateCartItem(int cartItemId, int quantity) {
        CartItem existingCartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart Item Not Found"));

        existingCartItem.setQuantity(quantity);
        cartItemRepository.save(existingCartItem);

        return existingCartItem.getCart();
    }

    public void removeCartItem(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public Cart getCart(int userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart Not Found"));
    }
}