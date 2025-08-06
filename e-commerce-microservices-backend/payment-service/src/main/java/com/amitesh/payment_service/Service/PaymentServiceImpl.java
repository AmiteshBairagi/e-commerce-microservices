package com.amitesh.payment_service.Service;

import com.amitesh.payment_service.Dto.PaymentRequestDto;
import com.amitesh.payment_service.Dto.PaymentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Override
    public PaymentResponseDto processPayment(PaymentRequestDto paymentRequestDto){

    }
}
