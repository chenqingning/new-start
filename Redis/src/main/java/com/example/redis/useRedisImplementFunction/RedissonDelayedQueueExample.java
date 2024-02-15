package com.example.redis.useRedisImplementFunction;

import org.redisson.Redisson;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonDelayedQueueExample {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("");
        RedissonClient redissonClient = Redisson.create(config);
        RQueue<String> redPacketQueue = redissonClient.getQueue("redPacketQueue");
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(redPacketQueue);
        String redPacketId = "redPacket123";
        delayedQueue.offer(redPacketId, 24, TimeUnit.HOURS);
        if (redPacketQueue.contains(redPacketId)){

        }
    }
}
