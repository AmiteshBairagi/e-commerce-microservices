package com.amitesh.cart_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"cart", "productId"})
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    private int productId;
    private int quantity;
    private double price;

    public CartItem( Cart cart, int productId, int quantity, double price) {

        this.cart = cart;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
}
