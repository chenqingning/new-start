package com.example.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/q")
    public void testEvent() {
        orderService.payFinished();
    }
}
