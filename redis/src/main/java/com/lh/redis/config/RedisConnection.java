package com.lh.redis.config;

import lombok.Data;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Data
public class RedisConnection {

    private JedisPoolConfig jedisPoolConfig;

    private String host;

    private Integer port;

    private String password;

    private Integer timeout;

    private String clientName = null;

    private JedisPool jedisPool;

    private void buildConnection() {
        if (jedisPool == null) {
            if (jedisPoolConfig == null) {
                jedisPool = new JedisPool(new JedisPoolConfig(), host, port, timeout, password, 0, clientName);
            } else {
                jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, 0, clientName);
            }
        }
    }

    public Jedis getJedis() {
        buildConnection();
        if (jedisPool != null) {
            return jedisPool.getResource();
        }

        return null;
    }
}
