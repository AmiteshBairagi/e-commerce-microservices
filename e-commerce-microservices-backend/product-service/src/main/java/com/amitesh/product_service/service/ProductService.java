package com.amitesh.product_service.service;


import com.amitesh.product_service.dto.ProductDto;
import com.amitesh.product_service.model.Product;
import com.amitesh.product_service.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;
    @Autowired
    private ModelMapper modelMapper;


    public List<Product> getProducts() {
        return repo.findAll();
    }

    public ResponseEntity<Product> getProductById(int id) {
        Product product = repo.findById(id).orElse(null);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else return new ResponseEntity<>(product,HttpStatus.OK);

    }

    public ResponseEntity<ProductDto> getProductDtoById(int id) {
        Product product = repo.findById(id).orElse(null);
        ProductDto productDto = new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getBrand(),
                product.getPrice(),
                product.getCategory(),
                product.isAvailability(),
                product.getImageName(),
                product.getImageType(),
                product.getImageData() == null ? "" : Base64.getEncoder().encodeToString(product.getImageData())


        );

        return new ResponseEntity<>(productDto,HttpStatus.OK);

    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        repo.save(product);
        return product;
    }

    public ResponseEntity<byte[]> getImageById(int imageId) {
        Product product = repo.findById(imageId).orElse(null);

        try{
            return new ResponseEntity<>(product.getImageData(),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());

        return repo.save(product);
    }

    public void deleteProduct(int id){
        repo.deleteById(id);
    }


}
