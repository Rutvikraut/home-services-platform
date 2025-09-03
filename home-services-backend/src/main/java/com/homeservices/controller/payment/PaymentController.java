package com.homeservices.controller.payment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeservices.dto.request.PaymentVerificationDto;
import com.homeservices.entities.Booking;
import com.homeservices.service.partner.PartnerService;
import com.homeservices.service.payment.PaymentService;
import com.homeservices.service.payment.PaymentServiceImpl;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {
	
	private final PaymentService paymentService;

    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody PaymentVerificationDto dto) {
        try {
            boolean verified = paymentService.verifyPayment(dto);

            if (verified) {
                return ResponseEntity.ok("Payment verified and booking updated.");
            } else {
                return ResponseEntity.badRequest().body("Invalid signature");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Payment verification failed");
        }
    }
    
    @PostMapping("/create-payment-order")
    public ResponseEntity<Map<String, String>> createOrder(@RequestBody Map<String, Object> data) throws RazorpayException {
        int amount = (Integer) data.get("amount");

        RazorpayClient client = new RazorpayClient("rzp_test_wrOmk2JCVPRQZo", "4KdC3NH5WpzOYpXH2voMbR5r");

        JSONObject options = new JSONObject();
        options.put("amount", amount * 100);
        options.put("currency", "INR");
        options.put("receipt", "txn_" + UUID.randomUUID().toString());

        com.razorpay.Order razorpayOrder = client.orders.create(options);
        
        Map<String, String> response = new HashMap<String,String>();
        response.put("orderId", razorpayOrder.get("id"));

        return ResponseEntity.ok(response);
    }


}
