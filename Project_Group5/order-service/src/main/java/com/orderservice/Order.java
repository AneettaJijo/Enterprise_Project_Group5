package com.orderservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    
    @NotBlank(message = "User ID is required")
    private String userId;
    
    @NotBlank(message = "Stock symbol is required")
    private String stockSymbol;
    
    @Positive(message = "Quantity must be greater than 0")
    private int quantity;
    
    @Positive(message = "Price must be greater than 0")
    private double price;
    
    private String status;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getStockSymbol() { return stockSymbol; }
    public void setStockSymbol(String symbol) { this.stockSymbol = symbol; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Order{id='" + id + "', userId='" + userId + "', stockSymbol='" + stockSymbol + "', quantity=" + quantity + ", price=" + price + ", status='" + status + "'}";
    }
}