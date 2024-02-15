package com.example.redis.useRedisImplementFunction;

import com.google.common.collect.ImmutableList;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class SlideWindow {
    static Jedis jedis = new Jedis();

    public boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) {
        String key = this.key(userId, actionKey);
        long ts = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        pipeline.isInMulti();
        pipeline.zadd(key, ts, String.valueOf(ts));
        pipeline.zremrangeByScore(key, 0, ts - maxCount);
        Response<Long> count = pipeline.zcard(key);
        pipeline.expire(key, period);
        pipeline.exec();
        pipeline.sync();
        return count.get() <= maxCount;

    }

    /**
     * 限流
     *
     * @param userId
     * @param actionKey
     * @return
     */
    private String key(String userId, String actionKey) {
        return String.format("limit:%s:%s", userId, actionKey);
    }

    public boolean isActionAllowedByLua(String userId, String actionKey, int period, int maxCount) {
        String luaScript = this.buildLuaScript();
        String key = this.key(userId, actionKey);
        long ts = System.currentTimeMillis();
        ImmutableList<String> keys = ImmutableList.of(String.valueOf(ts));
        ImmutableList<String> args = ImmutableList.of(String.valueOf(ts), String.valueOf((ts - period * 1000)),
                String.valueOf(period));
        Number count = (Number) jedis.eval(luaScript);
        return count != null && count.intValue() <= maxCount;


    }

    private String buildLuaScript() {
        return "redis.call('zadd',KEYS[1],tonumber(ARGV[1]),ARGV[1])" +
                "\nlocal c" +
                "\nc=redis.call('ZCARD', KEYS[1])" +
                "\nredis.call('ZREMRANGEBYSCORE', KEYS[1], 0, tonumber(ARGV[2]))" +
                "\nredis.call('EXPIRE', KEYS[1], tonumber(ARGV[3]))" +
                "\nreturn c;";
    }
}
