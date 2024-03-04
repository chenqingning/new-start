package com.example.spring.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void payFinished() {
        PayPublishedEvent payPublishedEvent = new PayPublishedEvent();
        payPublishedEvent.setPayName("首次监听");
        applicationEventPublisher.publishEvent(payPublishedEvent);
    }

}
