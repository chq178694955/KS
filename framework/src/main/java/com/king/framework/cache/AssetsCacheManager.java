package com.king.framework.cache;

import com.king.framework.exception.CacheException;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public interface AssetsCacheManager {
    <K, V> AssetsCache<K, V> getCache(String var1) throws CacheException;
}
