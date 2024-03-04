package com.example.spring.ioc;

import org.springframework.stereotype.Component;

@Component
public class WeixinPay implements PayFacade {
    @Override
    public void pay() {
        //处理业务逻辑
    }

    @Override
    public Scene getSupportScene() {
        return Scene.Tencent;
    }
}
