package com.example.hotel_app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuItem {

	@JsonProperty("Menu") 
    private String Menu;
	
	@JsonProperty("Submenu")
    private String Submenu;
	
	 @JsonProperty("price")  
    private int price;

    // Getters and Setters
    public String getMenu() {
        return Menu;
    }

    public void setMenu(String menu) {
        this.Menu = menu;
    }

    public String getSubmenu() {
        return Submenu;
    }

    public void setSubmenu(String submenu) {
        this.Submenu = submenu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "Menu='" + Menu + '\'' +
                ", Submenu='" + Submenu + '\'' +
                ", price=" + price +
                '}';
    }
}
