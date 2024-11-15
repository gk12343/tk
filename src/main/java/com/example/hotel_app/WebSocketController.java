package com.example.hotel_app;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Handle order acceptance
    @MessageMapping("/order_accept") // Handle messages sent to '/app/order_accept'
    public void acceptOrder(String orderDetails) {
        // Process the order or perform some business logic
        System.out.println("Order accepted: " + orderDetails);

        // Simulate sending an order accepted message to the subscribed clients
        messagingTemplate.convertAndSend("/topic/order_accept", new OrderDetails(orderDetails));
    }
    
    // You can create a method to handle any other type of message or event here

    public static class OrderDetails {
        private String filename;

        public OrderDetails(String filename) {
            this.filename = filename;
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }
}
