package com.example.spring.beanfactory;

import org.springframework.beans.factory.FactoryBean;

public class SelfFactoryBean<T> implements FactoryBean<T> {
    @Override
    public T getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
