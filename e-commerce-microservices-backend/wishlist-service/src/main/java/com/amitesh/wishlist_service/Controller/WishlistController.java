package com.amitesh.wishlist_service.Controller;

import com.amitesh.wishlist_service.Dto.WishlistItemDto;
import com.amitesh.wishlist_service.Model.Wishlist;
import com.amitesh.wishlist_service.Model.WishlistItem;
import com.amitesh.wishlist_service.Service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add")
    public ResponseEntity<Wishlist>  addToWishlist(WishlistItemDto wishlistItemDto){
        return new ResponseEntity<>(wishlistService.addToWishlist(wishlistItemDto), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{wishlistItemId}")
    public ResponseEntity<String> removeFromWishlist(@PathVariable int wishlistItemId){
        wishlistService.removeFromWishlist(wishlistItemId);
        return new ResponseEntity<>("Product Successfully Removed From The Wishlist ",HttpStatus.OK);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Wishlist> getWishlist(@PathVariable int userId){
        return new ResponseEntity<>(wishlistService.getWishlist(userId),HttpStatus.OK);
    }

}
