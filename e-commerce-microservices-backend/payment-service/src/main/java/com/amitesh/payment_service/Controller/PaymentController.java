package com.amitesh.payment_service.Controller;

import com.amitesh.payment_service.Dto.PaymentRequestDto;
import com.amitesh.payment_service.Dto.PaymentResponseDto;
import com.amitesh.payment_service.Service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")

public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }


    @PostMapping("/process")
    public ResponseEntity<PaymentResponseDto> processPayment(@RequestBody PaymentRequestDto paymentRequestDto){
        return new ResponseEntity<>(paymentService.processPayment(paymentRequestDto), HttpStatus.OK);
    }
}
