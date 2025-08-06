package com.amitesh.payment_service.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    private int id;
    private int userId;
    private int orderId;
    private double amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime paymentDate;


}
