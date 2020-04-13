package com.king.framework.utils;

import com.king.framework.cache.AssetsCacheManager;
import com.king.framework.cache.HDirection;
import com.king.framework.cache.impl.NativeAssetsCacheManager;
import com.king.framework.lock.ILock;
import com.king.framework.lock.impl.NativeLockImpl;
import com.king.framework.lock.impl.RedisLockImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
@Component
public class CacheUtils {
    private static final String REDIS_TEMPLATE_NAME = "redisTemplate";
    @Autowired
    private static RedisTemplate<String,Object> redisTemplate;
//    private static RedisTemplate<String, Object> redisTemplate = (RedisTemplate)BeanLoader.getBean("redisTemplate");
    private static AssetsCacheManager assetsCacheManager = new NativeAssetsCacheManager();

    public CacheUtils() {
    }

    public static Object get(String cacheName, String key) {
        return assetsCacheManager.getCache(cacheName).get(key);
    }

    public static void put(String cacheName, String key, Object value) {
        assetsCacheManager.getCache(cacheName).put(key, value);
    }

    public static void put(String cacheName, String key, Object value, long timeout, TimeUnit unit) {
        assetsCacheManager.getCache(cacheName).put(key, value, timeout, unit);
    }

    public static void remove(String cacheName, String key) {
        assetsCacheManager.getCache(cacheName).remove(key);
    }

    /** @deprecated */
    @Deprecated
    public static void clear(String cacheName) {
        assetsCacheManager.getCache(cacheName).clear();
    }

    /** @deprecated */
    @Deprecated
    public static int size(String cacheName) {
        return assetsCacheManager.getCache(cacheName).size();
    }

    /** @deprecated */
    @Deprecated
    public static Set<Object> keys(String cacheName) {
        return assetsCacheManager.getCache(cacheName).keys();
    }

    /** @deprecated */
    @Deprecated
    public static Set<Object> keys(String cacheName, String pattern) {
        return assetsCacheManager.getCache(cacheName).keys(pattern);
    }

    public static boolean exist(String cacheName, String key) {
        return assetsCacheManager.getCache(cacheName).exist(key);
    }

    public static long increment(String cacheName, String key) {
        return assetsCacheManager.getCache(cacheName).increment(key);
    }

    public static long decrement(String cacheName, String key) {
        return assetsCacheManager.getCache(cacheName).decrement(key);
    }

    public static void putList(String cacheName, String key, List<Object> values) {
        assetsCacheManager.getCache(cacheName).putList(key, values);
    }

    public static void putList(String cacheName, String key, List<Object> values, long timeout, TimeUnit unit) {
        assetsCacheManager.getCache(cacheName).putList(key, values, timeout, unit);
    }

    public static Long putList(String cacheName, String key, Object value, HDirection direction) {
        return assetsCacheManager.getCache(cacheName).putList(key, value, direction);
    }

    public Object popList(String cacheName, String key, HDirection direction) {
        return assetsCacheManager.getCache(cacheName).popList(key, direction);
    }

    public static Long getListSize(String cacheName, String key) {
        return assetsCacheManager.getCache(cacheName).getListSize(key);
    }

    public static List<Object> getList(String cacheName, String key, int start, int end) {
        return assetsCacheManager.getCache(cacheName).getList(key, start, end);
    }

    public static void putSet(String cacheName, String key, Set<Object> values, long timeout, TimeUnit unit) {
        assetsCacheManager.getCache(cacheName).putSet(key, values, timeout, unit);
    }

    public static void putSet(String cacheName, String key, Set<Object> values) {
        assetsCacheManager.getCache(cacheName).putSet(key, values);
    }

    public static void putSet(String cacheName, String key, Object value) {
        assetsCacheManager.getCache(cacheName).putSet(key, value);
    }

    public static Set<Object> getSet(String cacheName, String key) {
        return assetsCacheManager.getCache(cacheName).getSet(key);
    }

    public static boolean isSetMember(String cacheName, String key, Object value) {
        return assetsCacheManager.getCache(cacheName).isSetMember(key, value);
    }

    public static Long getSetSize(String cacheName, String key) {
        return assetsCacheManager.getCache(cacheName).getSetSize(key);
    }

    public static void putZset(String cacheName, String key, Set<Object> values, long timeout, TimeUnit unit) {
        assetsCacheManager.getCache(cacheName).putZset(key, values, timeout, unit);
    }

    public static void putZset(String cacheName, String key, Set<Object> values) {
        assetsCacheManager.getCache(cacheName).putZset(key, values);
    }

    public static void putZset(String cacheName, String key, Object value) {
        assetsCacheManager.getCache(cacheName).putZset(key, value);
    }

    public static Set<Object> getZset(String cacheName, String key, int start, int end) {
        return assetsCacheManager.getCache(cacheName).getZset(key, start, end);
    }

    public static Long getZsetSize(String cacheName, String key) {
        return assetsCacheManager.getCache(cacheName).getZsetSize(key);
    }

    public void putMap(String cacheName, String key, String mapKey, Object mapValue) {
        assetsCacheManager.getCache(cacheName).putMap(key, mapKey, mapValue);
    }

    public void delMap(String cacheName, String key, String mapKey) {
        assetsCacheManager.getCache(cacheName).delMap(key, mapKey);
    }

    public Object getMap(String cacheName, String key, String mapKey) {
        return assetsCacheManager.getCache(cacheName).getMap(key, mapKey);
    }

    public List<Object> getMapValues(String cacheName, String key) {
        return assetsCacheManager.getCache(cacheName).getMapValues(key);
    }

    public Long getMapSize(String cacheName, String key) {
        return assetsCacheManager.getCache(cacheName).getMapSize(key);
    }

    public static ILock getLock(String lockKey, int timeoutMsecs) {
        return (ILock)(redisTemplate != null ? new RedisLockImpl(redisTemplate, lockKey, timeoutMsecs) : new NativeLockImpl(lockKey, timeoutMsecs));
    }

    public static ILock getLock(String lockKey, int timeoutMsecs, int expireMsecs) {
        return (ILock)(redisTemplate != null ? new RedisLockImpl(redisTemplate, lockKey, timeoutMsecs, expireMsecs) : new NativeLockImpl(lockKey, timeoutMsecs, expireMsecs));
    }

    public void setAssetsCacheManager(AssetsCacheManager assetsCacheManager) {
        assetsCacheManager = assetsCacheManager;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        redisTemplate = redisTemplate;
    }
}