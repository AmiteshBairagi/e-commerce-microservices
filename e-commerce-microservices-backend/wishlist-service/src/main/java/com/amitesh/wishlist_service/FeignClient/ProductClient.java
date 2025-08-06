package com.amitesh.wishlist_service.FeignClient;


import com.amitesh.wishlist_service.Dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/productDto/{id}")
    public ResponseEntity<ProductDto> getProductDtoById(@PathVariable int id);
}
