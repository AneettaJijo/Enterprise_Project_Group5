package com.marketservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "market")
public class Market {
    @Id
    private String id;
    private String orderId;
    private String stockSymbol;
    private int quantity;
    private String status;

    // Constructors
    public Market() {}

    public Market(String orderId, String stockSymbol, int quantity, String status) {
        this.orderId = orderId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.status = status;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getStockSymbol() { return stockSymbol; }
    public void setStockSymbol(String stockSymbol) { this.stockSymbol = stockSymbol; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
