package com.example.spring.ioc;

import org.springframework.beans.factory.InitializingBean;

public interface PayFacade extends InitializingBean {
    void pay();

    Scene getSupportScene();

    @Override
    default void afterPropertiesSet() {
        PayFactory.register(getSupportScene(), this);
    }

}
