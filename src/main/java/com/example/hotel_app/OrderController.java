package com.example.hotel_app;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // For WebSocket messaging

    @PostMapping("/save_json")
    public Map<String, Object> saveJson(@RequestBody Map<String, Object> data) {
        String tableName = (String) data.getOrDefault("table_name", "default_table");
        Map<String, Object> orderData = (Map<String, Object>) data.get("order_data");

        // Define the file path and name
        String filePath = Paths.get(System.getProperty("user.dir"), tableName + ".json").toString();

        // Save the data to a JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter fileWriter = new FileWriter(new File(filePath))) {
            // Write the order data to a file in pretty format
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, orderData);
            
            // Read the file content to send via WebSocket
            String fileContent = objectMapper.writeValueAsString(orderData);

            // Emit a WebSocket message to notify clients
            messagingTemplate.convertAndSend("/topic/file_update", Map.of(
                "filename", tableName,
                "content", fileContent
            ));

            return Map.of("status", "success", "message", "Data saved to " + tableName + ".json");
        } catch (IOException e) {
            return Map.of("status", "error", "message", "Failed to save data: " + e.getMessage());
        }
    }
}
