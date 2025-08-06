package com.amitesh.order_service.Model;

import com.amitesh.order_service.Dto.OrderItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items  = new ArrayList<>();
    private String status;
    private double totalPrice;
    private LocalDateTime orderdate;

    public Order(int userId, List<OrderItem> items,String status, double totalPrice, LocalDateTime orderdate) {

        this.userId = userId;
        this.items = items;
        this.status = status;
        this.totalPrice = totalPrice;
        this.orderdate = orderdate;
    }
}
