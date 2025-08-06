package com.amitesh.cart_service.FeignClient;

import com.amitesh.cart_service.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {


    @GetMapping("product/productDto/{id}")
    public ResponseEntity<ProductDto> getProductDtoById(@PathVariable int id);
}
