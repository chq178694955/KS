package com.king.framework.cache.impl;

import com.king.framework.cache.AssetsCache;
import com.king.framework.cache.AssetsCacheManager;
import com.king.framework.exception.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class NativeAssetsCacheManager implements AssetsCacheManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ConcurrentMap<String, AssetsCache> caches = new ConcurrentHashMap();

    public NativeAssetsCacheManager() {
    }

    public <K, V> AssetsCache<K, V> getCache(String name) throws CacheException {
        this.logger.debug("Get NativeCache instance which name is : " + name);
        AssetsCache<K, V> c = (AssetsCache)this.caches.get(name);
        if (c == null) {
            c = new NativeAssetsCache(name);
            this.caches.put(name, c);
        }

        return (AssetsCache)c;
    }
}
