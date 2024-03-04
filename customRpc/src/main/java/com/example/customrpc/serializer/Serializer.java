package com.example.customrpc.serializer;

public interface Serializer {
    /**
     * 序列化
     * @param object 要序列化的对象
     * @return 字节数组
     */
    byte[] serialize(Object object);

    /**
     * 反序列
     * @param bytes 序列化后的字节数组
     * @param tClass 目标类
     * @return 反序列化的对象
     * @param <T> 类的类型。
     */

    <T> T deserialize(byte[] bytes, Class<T> tClass);

}
