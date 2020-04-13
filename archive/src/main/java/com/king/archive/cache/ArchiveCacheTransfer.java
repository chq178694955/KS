package com.king.archive.cache;

import org.springframework.cache.ehcache.EhCacheCacheManager;

public class ArchiveCacheTransfer {

    public void setCacheManager(EhCacheCacheManager ehCacheManager) {
        
        if (ehCacheManager != null) {
            ArchiveCache.setLocalCacheManager(ehCacheManager.getCacheManager());
        }
    }
    
}
