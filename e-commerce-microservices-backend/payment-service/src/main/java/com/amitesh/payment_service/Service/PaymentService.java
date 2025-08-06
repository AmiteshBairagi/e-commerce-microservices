package com.amitesh.payment_service.Service;

import com.amitesh.payment_service.Dto.PaymentRequestDto;
import com.amitesh.payment_service.Dto.PaymentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface PaymentService {

    public PaymentResponseDto processPayment(PaymentRequestDto paymentRequestDto);
}
