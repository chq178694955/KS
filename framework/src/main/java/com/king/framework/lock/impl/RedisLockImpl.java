package com.king.framework.lock.impl;

import com.king.framework.lock.ILock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class RedisLockImpl implements ILock {
    private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;
    protected Logger logger;
    private int expireMsecs;
    private int timeoutMsecs;
    private String lockKey;
    private String lockVal;
    private volatile boolean locked;
    private RedisTemplate<String, Object> redisTemplate;

    public RedisLockImpl(RedisTemplate<String, Object> redisTemplate, String lockKey) {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.expireMsecs = 60000;
        this.timeoutMsecs = 10000;
        this.lockVal = "";
        this.locked = false;
        this.redisTemplate = redisTemplate;
        this.lockKey = "lock_cache:" + lockKey;
    }

    public RedisLockImpl(RedisTemplate<String, Object> redisTemplate, String lockKey, int timeoutMsecs) {
        this(redisTemplate, lockKey);
        this.timeoutMsecs = timeoutMsecs;
    }

    public RedisLockImpl(RedisTemplate<String, Object> redisTemplate, String lockKey, int timeoutMsecs, int expireMsecs) {
        this(redisTemplate, lockKey, timeoutMsecs);
        this.expireMsecs = expireMsecs;
    }

    public synchronized boolean lock() {
        int timeout = this.timeoutMsecs;

        while(timeout >= 0) {
            this.lockVal = String.valueOf(this.getCurTimeMillis() + (long)this.expireMsecs + 1L);
            if (this.setNX(this.lockKey, this.lockVal)) {
                this.locked = true;
                return true;
            }

            String curValue = this.get(this.lockKey);
            if (curValue != null && Long.parseLong(curValue) < this.getCurTimeMillis()) {
                String oldValue = this.getSet(this.lockKey, this.lockVal);
                if (oldValue != null && oldValue.equals(curValue)) {
                    this.locked = true;
                    return true;
                }
            }

            timeout -= 100;

            try {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("=====================取redis锁时等待超时,线程休眠100ms=======================");
                }

                Thread.sleep(100L);
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }
        }

        return false;
    }

    public synchronized void unlock() {
        if (this.locked && this.lockVal != null && this.lockVal.equals(this.get(this.lockKey))) {
            this.redisTemplate.delete(this.lockKey);
            this.locked = false;
        }

    }

    public String getKey() {
        return this.lockKey;
    }

    public boolean isLocked() {
        return this.locked;
    }

    private long getCurTimeMillis() {
        return (Long)this.redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.time();
            }
        });
    }

    private String get(final String key) {
        Object obj = null;

        try {
            obj = this.redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] data = connection.get(serializer.serialize(key));
                    connection.close();
                    return data == null ? null : serializer.deserialize(data);
                }
            });
        } catch (Exception var4) {
            this.logger.error("get redis error, key : " + key);
        }

        return obj != null ? obj.toString() : null;
    }

    private boolean setNX(final String key, final String value) {
        Object obj = null;

        try {
            obj = this.redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Boolean success = connection.setNX(serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return success;
                }
            });
        } catch (Exception var5) {
            this.logger.error("redis setNX error, key : " + key);
        }

        return obj != null ? (Boolean)obj : false;
    }

    private String getSet(final String key, final String value) {
        Object obj = null;

        try {
            obj = this.redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] ret = connection.getSet(serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return serializer.deserialize(ret);
                }
            });
        } catch (Exception var5) {
            this.logger.error("redis getSet error, key : " + key);
        }

        return obj != null ? (String)obj : null;
    }
}
