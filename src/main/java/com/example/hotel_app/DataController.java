package com.example.hotel_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class DataController {

    @GetMapping("/data")
    public List<MenuItem> getData() throws IOException {
        // Path to the JSON file (relative path)
        String filePath = "src/main/resources/templates/menu.json";  // Adjust path based on your setup

        // Use Jackson's ObjectMapper to read the file into a List of MenuItem objects
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize the JSON content from the file into a List<MenuItem>
        List<MenuItem> data = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, MenuItem.class));

        // Print the loaded data to the console (for debugging)
        System.out.println(data);

        return data;  // Return the list of MenuItem objects, which will be automatically converted to JSON
    }
    
    
    
    
    
}
