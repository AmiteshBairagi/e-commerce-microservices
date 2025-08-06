package com.amitesh.wishlist_service.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;

    @OneToMany(mappedBy = "wishlist",cascade = CascadeType.ALL)
    private List<WishlistItem> items;

    public Wishlist(Integer o, int userId, ArrayList<WishlistItem> items) {    ////////// Study this constructor
    }
}
