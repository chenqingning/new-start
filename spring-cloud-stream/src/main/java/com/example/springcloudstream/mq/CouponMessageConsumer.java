package com.example.springcloudstream.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class CouponMessageConsumer {
    @Autowired
    private CouponUserService couponUserService;

    @StreamListener(RequestCouponQueue.INPUT)
    public void consumer(Message<RequestCoupon> message) {
        Coupon coupon = couponUserService.requestCoupon(message.getPayload());
    }

}
