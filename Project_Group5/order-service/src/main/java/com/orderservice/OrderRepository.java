package com.orderservice;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.orderservice.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
}
