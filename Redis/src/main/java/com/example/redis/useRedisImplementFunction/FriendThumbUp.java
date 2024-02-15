package com.example.redis.useRedisImplementFunction;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

import java.util.List;
import java.util.stream.Collectors;

public class FriendThumbUp {
    private static final String LIKE_PREFIX = "like:";
    private static final String USER_PREFIX = "user:";
    static Jedis jedis = new Jedis();

    public static void likePost(String postId, String userId) {
        String key = LIKE_PREFIX + postId;
        Long now = System.currentTimeMillis();
        jedis.zadd(key, now.doubleValue(), userId);
    }

    public static void unLikes(String postId, String userId) {
        String key = LIKE_PREFIX + postId;
        jedis.zrem(key, userId);
    }

    public List<String> getLikes(String postId) {
        String key = LIKE_PREFIX + postId;
        ZParams zParams = new ZParams();
        return jedis.zrandmemberWithScores(key, -1)
                .stream().map(tuple -> {
                    String userId = tuple.getElement();
                    return userId;
                }).collect(Collectors.toList());
    }
}
