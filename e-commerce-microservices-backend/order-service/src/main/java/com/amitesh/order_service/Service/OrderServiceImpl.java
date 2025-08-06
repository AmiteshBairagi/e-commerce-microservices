package com.amitesh.order_service.Service;

import com.amitesh.order_service.Dto.OrderItemDto;
import com.amitesh.order_service.Dto.OrderRequestDto;
import com.amitesh.order_service.Dto.OrderResponseDto;
import com.amitesh.order_service.Model.Order;
import com.amitesh.order_service.Model.OrderItem;
import com.amitesh.order_service.Repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        int userId = orderRequestDto.getUserId();
        double totalPrice = orderRequestDto.getTotalPrice();

        Order order = new Order();

        List<OrderItem> orderItems = orderRequestDto.getItems().stream().map(itemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(itemDto.getProductId());
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(itemDto.getPrice());
            return orderItem;
        }).toList();


        order.setUserId(userId);
        order.setItems(orderItems);
        order.setStatus("PLACED");
        order.setTotalPrice(totalPrice);
        order.setOrderdate(LocalDateTime.now());


        orderRepository.save(order);

        return new OrderResponseDto(order.getId(), order.getStatus(), order.getTotalPrice(), order.getOrderdate(), orderRequestDto.getItems());


    }

    @Override
    public List<OrderResponseDto> getUserOrders(@PathVariable int userId) {

        List<Order> orders = orderRepository.findByUserId(userId);

//        List<OrderResponseDto> orderResponseDtos =   orders.stream().map(order -> {
//            List<OrderItem> orderItems = order.getItems();
//            int orderId = order.getId();
//            String status = order.getStatus();
//            double totalPrice = order.getTotalPrice();
//            LocalDateTime orderDate = order.getOrderdate();
//
//            List<OrderItemDto> orderItemDtos = orderItems.stream().map(orderItem -> {
//                int productId = orderItem.getProductId();
//                int quantity = orderItem.getQuantity();
//                int price = orderItem.getPrice();
//
//                OrderItemDto orderItemDto = new OrderItemDto();
//                orderItemDto.setProductId(productId);
//                orderItemDto.setQuantity(quantity);
//                orderItemDto.setPrice(price);
//
//                return orderItemDto;
//            }).toList();
//
//            OrderResponseDto orderResponseDto = new OrderResponseDto();
//
//            orderResponseDto.setOrderDate(orderDate);
//            orderResponseDto.setOrderId(orderId);
//            orderResponseDto.setStatus(status);
//            orderResponseDto.setTotalPrice(totalPrice);
//            orderResponseDto.setItems(orderItemDtos);
//
//            return orderResponseDto;
//        }).toList();
//
//        return orderResponseDtos;


        return orders.stream().
                map(order -> new OrderResponseDto(
                        order.getId(),
                        order.getStatus(),
                        order.getTotalPrice(),
                        order.getOrderdate(),
                        order.getItems().stream()
                                .map(orderItem -> new OrderItemDto(
                                        orderItem.getProductId(),
                                        orderItem.getQuantity(),
                                        orderItem.getPrice()
                                )).toList()
                )).toList();
    }


    public OrderResponseDto getOrderById(int orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));

        return new OrderResponseDto(
                order.getId(),
                order.getStatus(),
                order.getTotalPrice(),
                order.getOrderdate(),
                order.getItems().stream().map(orderItem -> new OrderItemDto(
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice()
                )).toList()
        );
    }

    public String cancelOrder(int orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));
        order.setStatus("CANCELLED");
        orderRepository.save(order);

        return "Order Cancelled Successfully";
    }

}