package com.example.hotel_app;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class order_accept {
	
	    private final SimpMessagingTemplate messagingTemplate;

	    public order_accept(SimpMessagingTemplate messagingTemplate) {
	        this.messagingTemplate = messagingTemplate;
	    }

	    @PostMapping("/accept_order")
	    public void acceptOrder() {
	        messagingTemplate.convertAndSend("/topic/order_accept", new WebSocketController.OrderDetails("order_accepted"));
	    }
	    
	    @PostMapping("/reject_order")
	    public void rejectorder() {
	        messagingTemplate.convertAndSend("/topic/order_accept", new WebSocketController.OrderDetails("order_rejected"));
	    }
	}



