package com.example.basejava.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericTypeReference<T> {
    private final Class<T> type;

    protected GenericTypeReference() {
        Type superClass = getClass().getGenericSuperclass();
        this.type = (Class<T>) ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Class<T> getType() {
        return this.type;
    }

    public static void main(String[] args) {
        // 创建匿名子类
        GenericTypeReference<String> genericTypeRef = new GenericTypeReference<String>() {};
        System.out.println(genericTypeRef.getType()); // 输出泛型类型
    }
}