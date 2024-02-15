package com.example.redis.stream;

import org.redisson.Redisson;
import org.redisson.api.RStream;
import org.redisson.api.RedissonClient;
import org.redisson.api.StreamMessageId;
import org.redisson.api.stream.StreamAddArgs;
import org.redisson.api.stream.StreamAddParams;
import org.redisson.api.stream.StreamMultiReadGroupArgs;

import java.util.HashMap;
import java.util.Map;

import static org.redisson.api.StreamMessageId.AUTO_GENERATED;

public class Stream {
    public static void main(String[] args) {
        RedissonClient redissonClient = Redisson.create();
        RStream<String, String> stream = redissonClient.getStream("my-stream");
        Map<String, String> map = new HashMap<>();
        StreamAddArgs<String, String> streamAddArgs = StreamAddArgs.<String, String>entry("15", "17");
        StreamMessageId streamMessageId = AUTO_GENERATED;
        stream.add(streamMessageId, streamAddArgs);
        stream.createGroup("mygroup");
        stream.createConsumer("mygroup", "myconsumer");
        stream.readGroup("mygroup", "myconsumer",
                StreamMultiReadGroupArgs.greaterThan(StreamMessageId.NEWEST, "", StreamMessageId.NEWEST));

    }
}
