package com.example.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EdmListener {
    @EventListener
    public void getEvent(PayPublishedEvent p) {
        System.out.println("edm:" + p.getPayName());

    }
}
