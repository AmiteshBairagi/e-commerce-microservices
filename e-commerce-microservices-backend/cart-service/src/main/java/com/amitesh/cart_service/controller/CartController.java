package com.amitesh.cart_service.controller;

import com.amitesh.cart_service.dto.CartItemDto;
import com.amitesh.cart_service.model.Cart;
import com.amitesh.cart_service.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody CartItemDto cartItemDto){
        return new ResponseEntity<>(cartService.addToCart(cartItemDto), HttpStatus.OK);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable int cartItemId, @RequestParam int quantity){
        return new ResponseEntity<>(cartService.updateCartItem(cartItemId,quantity),HttpStatus.OK);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable int cartItemId){
        cartService.removeCartItem(cartItemId);
        return new ResponseEntity<>("Item Removed Successfully",HttpStatus.OK);
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable int userId){
        return new ResponseEntity<>(cartService.getCart(userId),HttpStatus.OK);
    }
}
