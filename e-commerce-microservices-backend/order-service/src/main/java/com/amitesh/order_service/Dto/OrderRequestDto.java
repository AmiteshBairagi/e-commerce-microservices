package com.amitesh.order_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    private int userId;
    private List<OrderItemDto> items = new ArrayList<>();
    private int totalPrice;
}
