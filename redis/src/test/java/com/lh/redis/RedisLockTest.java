package com.lh.redis;

import com.lh.redis.config.RedisConnection;
import com.lh.redis.util.RedisConnectionUtil;
import com.lh.redis.util.RedisTool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

@Slf4j
public class RedisLockTest {

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RedisConnection redisConnection = RedisConnectionUtil.create();
                    Jedis jedis = redisConnection.getJedis();
                    String value = RedisTool.fetchLockValue();
                    String key = "20211202";
                    boolean result = RedisTool.tryGetDistributedLock(jedis, key, value, 1000);
                    try {
                        log.info(Thread.currentThread().getName() + ", lock key: " + key + "  " + result);
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        boolean unLockResult = RedisTool.releaseDistributedLock(jedis, key, value);
                        log.info(Thread.currentThread().getName() + ", unlock key: " + key + "  " + unLockResult);
                    }
                }
            }).start();
        }
    }
}
