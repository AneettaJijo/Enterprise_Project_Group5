package com.project1;

import com.project1.Order;
import com.project1.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public Order placeOrder(Order order) {
        order.setStatus("PENDING");
        return repository.save(order);
    }
}
