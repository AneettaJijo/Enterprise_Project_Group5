package com.marketservice;

import com.marketservice.Market;
import com.marketservice.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class MarketService {
    @Autowired
    private MarketRepository marketRepository;

    public String placeStockOrder(String orderId, String stockSymbol, int quantity) {
        Market market = new Market(orderId, stockSymbol, quantity, "PLACED");
        marketRepository.save(market);
        return "Stock order placed in market with ID: " + market.getId();
    }

    public List<Market> getAllMarketOrders() {
        return marketRepository.findAll();
    }

    public Market getMarketOrderByOrderId(String orderId) {
        return marketRepository.findByOrderId(orderId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Market order not found for orderId: " + orderId));
    }
}