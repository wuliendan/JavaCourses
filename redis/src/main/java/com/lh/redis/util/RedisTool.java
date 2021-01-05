package com.lh.redis.util;

import com.lh.redis.config.RedisConnection;
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

    public static long setIncr(String key, RedisConnection redisConnection) {
        Jedis jedis = null;
        long total = 0;
        try {
            jedis = redisConnection.getJedis();
            if (jedis.get(key) == null) {
                //jedisInstance是Jedis连接实例，可以使单链接也可以使用链接池获取，实现方式请参考之前的blog内容
                //如果redis目前没有这个key，创建并赋予0，有效时间为60s
                jedis.setex(key, 5, "0");
            } else {
                //获取加1后的值
                total = jedis.incr(key);
                //Redis TTL命令以秒为单位返回key的剩余过期时间。当key不存在时，返回-2。当key存在但没有设置剩余生存时间时，返回-1。否则，以秒为单位，返回key的剩余生存时间。
                if (jedis.ttl(key) == -1L) {
                    //为给定key设置生存时间，当key过期时(生存时间为0)，它会被自动删除。
                    jedis.expire(key, 5);
                }
            }
        } finally {
            jedis.close();
        }

        return total;
    }
}
