package com.lh.redis.util;

import com.lh.redis.config.RedisConnection;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 连接测试辅助类
 */
public class RedisConnectionUtil {
    public static RedisConnection create() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(1);
        RedisConnection redisConnection = new RedisConnection();
        redisConnection.setHost("127.0.0.1");
        redisConnection.setPort(6379);
        redisConnection.setClientName(Thread.currentThread().getName());
        redisConnection.setTimeout(3000);
        redisConnection.setJedisPoolConfig(jedisPoolConfig);
        return redisConnection;
    }
}
