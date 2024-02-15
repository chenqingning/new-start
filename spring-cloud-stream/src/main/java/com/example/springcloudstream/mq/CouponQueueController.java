package com.example.springcloudstream.mq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/")
public class CouponQueueController {
    @Autowired
    private RequestCouponQueue requestCouponQueue;

    @PostMapping("requestCouponQueue")
    public String requestCouponQueue(@Valid @RequestBody RequestCoupon requestCoupon) {
        Message message = MessageBuilder.withPayload(requestCoupon).build();
        requestCouponQueue.output().send(message);
        return "请稍后到账户查询优惠券发放情况";
    }
}
