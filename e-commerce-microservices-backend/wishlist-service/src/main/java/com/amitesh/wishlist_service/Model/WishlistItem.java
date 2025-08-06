package com.amitesh.wishlist_service.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wishlist_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "wishlist_id" , referencedColumnName = "id")
    private Wishlist wishlist;

    private int productId;

    public WishlistItem(Wishlist wishlist, int productId) {
        this.wishlist = wishlist;
        this.productId = productId;
    }
}
