package com.amitesh.order_service.Dto;


import com.amitesh.order_service.Model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDto {

    private int orderId;
    private String status;
    private double totalPrice;
    private LocalDateTime orderDate;
    private List<OrderItemDto> items = new ArrayList<>();


}
