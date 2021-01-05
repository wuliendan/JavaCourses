package com.lh.redis.util;

import redis.clients.jedis.Jedis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     *
     * @param jedis jedis
     * @param lockKey lockKey
     * @param requestId requestId
     * @param expireTime expire Time
     * @return boolean
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }

        return false;
    }

    /**
     * 释放分布式锁
     *
     * @param jedis jedis
     * @param lockKey lockKey
     * @param requestId requestId
     * @return boolean
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * 生成唯一的RequestId.
     *
     * @return 唯一字符串
     */
    public static String fetchLockValue() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return UUID.randomUUID().toString() + "_" + df.format(new Date());
    }
}
