package com.marketservice;

import com.marketservice.Market;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface MarketRepository extends MongoRepository<Market, String> {
    Optional<Market> findByOrderId(String orderId);
}