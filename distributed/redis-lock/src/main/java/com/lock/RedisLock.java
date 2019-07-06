package com.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Collections;


public class RedisLock {

    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_EXPIRE_TIME = "PX";

    private static final Long RELEASE_SUCCESS = 1L;

    public static boolean tryLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String res = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(res)) {
            logger.info("{} get lock", Thread.currentThread().getName());
            return true;
        }
        return false;
    }

    public static boolean unLock(Jedis jedis, String lockKey, String requestId) {
        String script = "if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        if (RELEASE_SUCCESS.equals(result)) {
            logger.info("{} release lock", Thread.currentThread().getName());
            return true;
        }
        return false;
    }


}
