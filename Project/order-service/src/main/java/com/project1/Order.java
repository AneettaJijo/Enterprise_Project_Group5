package com.project1;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    private String id;
    private String accountId;
    private String stockSymbol;
    private int quantity;
    private String status; // e.g., "PENDING", "PLACED"
}