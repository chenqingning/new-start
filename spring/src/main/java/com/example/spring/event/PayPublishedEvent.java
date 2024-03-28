package com.example.spring.event;

public class PayPublishedEvent {
    private String payName;

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayName() {
        return payName;
    }
}
