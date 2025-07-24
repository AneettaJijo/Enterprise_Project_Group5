package com.project1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String showForm() {
        return "order-form"; // Loads order-form.html
    }
}
