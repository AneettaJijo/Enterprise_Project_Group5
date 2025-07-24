package com.project1;

import com.project1.Order;
import com.project1.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    // Handle API POST requests from Postman or other services
    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        order.setStatus("PENDING");
        return service.placeOrder(order);
    }

    
}