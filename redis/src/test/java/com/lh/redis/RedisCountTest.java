package com.lh.redis;

import com.lh.redis.config.RedisConnection;
import com.lh.redis.util.RedisConnectionUtil;
import com.lh.redis.util.RedisTool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisCountTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                RedisConnection redisConnection = RedisConnectionUtil.create();
                try {
                    for (int i = 0; i < 100; i++) {
                        long num = RedisTool.setIncr("0", redisConnection);
                        log.info("num: " + num);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
