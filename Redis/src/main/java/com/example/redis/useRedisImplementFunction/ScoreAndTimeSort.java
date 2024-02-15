package com.example.redis.useRedisImplementFunction;

import redis.clients.jedis.Jedis;

public class ScoreAndTimeSort {
    private static final String ZSET_KEY = "my_zset";
    static Jedis jedis = new Jedis();

    public static void addMembers(String member, int score, long timestamp) {
        //+1是为了防止score是0，减去时间戳之后就变成了负数，Redis 有时会使用负数来表示错误或特殊情况
        double final_score = score + 1 - timestamp / 1e13;
        jedis.zadd(ZSET_KEY, final_score, member);
    }
}
