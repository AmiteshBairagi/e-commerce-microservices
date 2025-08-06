package com.amitesh.wishlist_service.Service;

import com.amitesh.wishlist_service.Dto.ProductDto;
import com.amitesh.wishlist_service.Dto.WishlistItemDto;
import com.amitesh.wishlist_service.FeignClient.ProductClient;
import com.amitesh.wishlist_service.Model.Wishlist;
import com.amitesh.wishlist_service.Model.WishlistItem;
import com.amitesh.wishlist_service.Repository.WishlistItemRepository;
import com.amitesh.wishlist_service.Repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    private  final ProductClient productClient;
    private final WishlistRepository wishlistRepository;
    private final WishlistItemRepository wishlistItemRepository;

    public WishlistService(ProductClient productClient, WishlistRepository wishlistRepository, WishlistItemRepository wishlistItemRepository) {
        this.productClient = productClient;
        this.wishlistRepository = wishlistRepository;
        this.wishlistItemRepository = wishlistItemRepository;
    }

    public Wishlist addToWishlist(WishlistItemDto wishlistItemDto) {
        int userId = wishlistItemDto.getUserId();
        int productId = wishlistItemDto.getProductId();

        //ProductDto productDto = productClient.getProductDtoById(productId).getBody();


        Wishlist existingWishlist = wishlistRepository.findByUserId(userId)
                .orElse(new Wishlist(null,userId,new ArrayList<>()));

        List<WishlistItem> items = existingWishlist.getItems();

        if(items != null){
            WishlistItem wishlistItem = new WishlistItem(existingWishlist,productId);
            items.add(wishlistItem);
        }else{
            WishlistItem wishlistItem = new WishlistItem(existingWishlist,productId);
            items.add(wishlistItem);
        }

        return wishlistRepository.save(existingWishlist);
    }

    public void removeFromWishlist(int wishlistItemId) {
        wishlistItemRepository.deleteById(wishlistItemId);
    }

    public Wishlist getWishlist(int userId) {
        return wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wishlist Not Found"));
    }
}
