package com.example.hotel_app;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "frontpage"; // Map to frontpage.html
    }

    @GetMapping("/backendpage.html")
    public String alerts() {
        return "backendpage"; // Map to alerts.html
    }

    @GetMapping("/payment")
    public String payment() {
        return "payment"; // Map to payment.html
    }

    @GetMapping("/table_book")
    public String table() {
        return "local_global_server_table_booking.html"; // Map to table.html
    }
}
