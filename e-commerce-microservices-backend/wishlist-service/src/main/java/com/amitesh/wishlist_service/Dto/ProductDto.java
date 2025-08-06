package com.amitesh.wishlist_service.Dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private int id;
    private String name;
    private String description;
    private String brand;
    private int price;
    private String category;
    private boolean availability;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;

}
