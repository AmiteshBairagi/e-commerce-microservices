package com.amitesh.payment_service.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
    private int userId;
    private int oredrId;
    private double amount;
    private String paymentMethod;
}
