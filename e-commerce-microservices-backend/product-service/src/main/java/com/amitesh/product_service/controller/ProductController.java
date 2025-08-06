package com.amitesh.product_service.controller;


import com.amitesh.product_service.dto.ProductDto;
import com.amitesh.product_service.model.Product;
import com.amitesh.product_service.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){

        return service.getProductById(id);
    }

    @GetMapping("/productDto/{id}")
    public ResponseEntity<ProductDto> getProductDtoById(@PathVariable int id){

        return service.getProductDtoById(id);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product , @RequestPart MultipartFile imageFile){

        try{
            Product newProduct = service.addProduct(product,imageFile);
            return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
        }catch(Exception e){
            //System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{imageId}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable int imageId){
        return service.getImageById(imageId);
    }


    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct (@PathVariable int id, @RequestPart Product product , @RequestPart MultipartFile imageFile){
        Product product1 = null;

        try{
            product1 = service.updateProduct(id,product,imageFile);
        }catch(IOException e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
        }


        if(product1 != null){
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product product = service.getProductById(id).getBody();

        if(product != null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Product not Found",HttpStatus.NOT_FOUND);
        }
    }

}
