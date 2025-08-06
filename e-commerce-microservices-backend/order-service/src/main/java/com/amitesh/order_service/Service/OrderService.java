package com.amitesh.order_service.Service;


import com.amitesh.order_service.Dto.OrderRequestDto;
import com.amitesh.order_service.Dto.OrderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface OrderService {
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);

    public List<OrderResponseDto> getUserOrders(int userId);

    public OrderResponseDto getOrderById(int orderId);

    public String cancelOrder( int orderId);
}
