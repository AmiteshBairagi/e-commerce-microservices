package com.amitesh.wishlist_service.Repository;


import com.amitesh.wishlist_service.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
    Optional<Wishlist> findByUserId(int userId);
}
