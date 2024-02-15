package com.example.redis.useRedisImplementFunction;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class ExpiryListener extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("Key expired:" + message);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        ExpiryListener expiryListener = new ExpiryListener();
        jedis.psubscribe(expiryListener, "__key*__:expired");
        jedis.set("myKey", "myValue");
        jedis.expire("myKey", 10); // 设置过期时间为10秒
        // 阻塞，等待过期事件
        try {
            Thread.sleep(15000); // 等待超过过期时间，确保过期事件发生
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 取消订阅
        expiryListener.unsubscribe();
        jedis.close();
    }
}
