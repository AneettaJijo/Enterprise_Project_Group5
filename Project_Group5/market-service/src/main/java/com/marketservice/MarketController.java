package com.marketservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/market")
public class MarketController {
    private static final Logger logger = LoggerFactory.getLogger(MarketController.class);

    @Autowired
    private MarketService marketService;

    @PostMapping("/placeStockOrder/{orderId}")
    public String placeStockOrder(@PathVariable String orderId, @RequestBody Map<String, Object> orderData) {
        logger.info("Received stock order request for orderId: {}", orderId);
        String stockSymbol = (String) orderData.get("stockSymbol");
        int quantity = ((Number) orderData.get("quantity")).intValue();
        return marketService.placeStockOrder(orderId, stockSymbol, quantity);
    }

    @GetMapping("/orders")
    public List<Market> getAllMarketOrders() {
        logger.info("Retrieving all market orders");
        return marketService.getAllMarketOrders();
    }

    @GetMapping("/orders/{orderId}")
    public Market getMarketOrderByOrderId(@PathVariable String orderId) {
        logger.info("Retrieving market order for orderId: {}", orderId);
        return marketService.getMarketOrderByOrderId(orderId);
    }
}