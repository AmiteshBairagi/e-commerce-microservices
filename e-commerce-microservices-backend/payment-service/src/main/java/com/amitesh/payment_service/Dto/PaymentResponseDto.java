package com.amitesh.payment_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

    private int userId;
    private int orderId;
    private int paymentId;
    private double amount;
    private String status;
    private LocalDateTime paymentDate;
    private String message;

}
