package com.example.redis.redlock;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class DistributedLock {
    public static void main(String[] args) throws InterruptedException {
        Config config1 = new Config();
        config1.useSingleServer().setAddress("redis://127.0.0.1:6379");
        Config config2 = new Config();
        config2.useSingleServer().setAddress("redis://127.0.0.1:6380");
        Config config3 = new Config();
        config3.useSingleServer().setAddress("redis://127.0.0.1:6381");
        RedissonClient redissonClient1 = Redisson.create(config1);
        RedissonClient redissonClient2 = Redisson.create(config1);
        RedissonClient redissonClient3 = Redisson.create(config1);
        RLock rLock1 = redissonClient1.getLock("localKey");
        RLock rLock2 = redissonClient2.getLock("localKey");
        RLock rLock3 = redissonClient3.getLock("localKey");
        RedissonRedLock redLock = new RedissonRedLock(rLock1, rLock2, rLock3);
        long waitTime = 10;
        long leaseTime = 30;
        boolean localResult = redLock.tryLock(waitTime, leaseTime, TimeUnit.MICROSECONDS);
        if (localResult) {
            try {

            } finally {
                redLock.unlock();
            }
        }
        redissonClient1.shutdown();
        redissonClient2.shutdown();
        redissonClient3.shutdown();
    }
}
