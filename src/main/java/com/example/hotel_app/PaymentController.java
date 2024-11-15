package com.example.hotel_app;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import ch.qos.logback.core.net.server.Client;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${razorpay.keyId}")
    private String razorpayKeyId;

    @Value("${razorpay.keySecret}")
    private String razorpayKeySecret;

   private RazorpayClient razorpayClient;
    
    

    // This is a constructor to initialize RazorpayClient
//    public PaymentController() throws Exception {
//        this.razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);
//    }
//    
    
   

    @PostMapping("/create-order")
    public Map<String, Object> createOrder(@RequestBody Map<String, Object> request) throws Exception {
        int amount = Integer.parseInt(request.get("amount").toString());  // Amount in paise
        Map<String, Object> orderRequest = new HashMap<>();
        orderRequest.put("amount", amount);
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "receipt#123");

        Order order = razorpayClient.orders.create((JSONObject) orderRequest);
        Map<String, Object> response = new HashMap<>();
        response.put("id", order.get("id"));
        response.put("amount", order.get("amount"));
        return response;
    }

    @PostMapping("/verify-payment")
    public Map<String, Object> verifyPayment(@RequestParam String razorpay_payment_id,
                                              @RequestParam String razorpay_order_id,
                                              @RequestParam String razorpay_signature) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, String> paymentData = new HashMap<>();
            paymentData.put("razorpay_order_id", razorpay_order_id);
            paymentData.put("razorpay_payment_id", razorpay_payment_id);
            paymentData.put("razorpay_signature", razorpay_signature);

           // razorpayClient.utility.verifyPaymentSignature(paymentData);
            response.put("status", "Payment successful");
        } catch (Exception e) {
            response.put("status", "Payment verification failed");
        }
        return response;
    }
}
