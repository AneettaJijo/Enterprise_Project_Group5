package com.project1;

import com.project1.Order;
import com.project1.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class FormSubmitController {

    private final OrderService orderService;

    // Handles form POST from Thymeleaf page
    @PostMapping("/place-order")
    public String submitOrder(
            @RequestParam String accountId,
            @RequestParam String stockSymbol,
            @RequestParam int quantity,
            Model model
    ) {
        Order order = Order.builder()
                .accountId(accountId)
                .stockSymbol(stockSymbol)
                .quantity(quantity)
                .status("PENDING")
                .build();

        orderService.placeOrder(order);
        model.addAttribute("message", "Order placed successfully!");
        return "order-form";
    }
}
