package com.example.redis.useRedisImplementFunction;

import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.stream.Collectors;

public class FindFriendNearByYou {
    static Jedis jedis = new Jedis();
    static final String USER_LOCATION_KEY = "user_location";

    public static void saveUserLocation(String userId, Double longitude, long latitude) {
        jedis.geoadd(USER_LOCATION_KEY, latitude, latitude, userId);
    }

    public static List<String> getNearByUsers(double longitude, long latitude, double radius) {
        List<GeoRadiusResponse> responses = jedis.georadius(USER_LOCATION_KEY, longitude, latitude, radius, GeoUnit.KM);
        return responses.stream().map(geo -> geo.getMemberByString()).collect(Collectors.toList());
    }
}
