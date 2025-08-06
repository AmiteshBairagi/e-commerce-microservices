package com.amitesh.wishlist_service.Repository;

import com.amitesh.wishlist_service.Model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem,Integer> {
}
