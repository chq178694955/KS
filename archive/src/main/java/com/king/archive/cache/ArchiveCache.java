package com.king.archive.cache;

import com.king.archive.dmo.ArchiveDmo;
import com.king.archive.dmo.NullDmo;
import com.king.archive.dto.ArchiveDto;
import com.king.archive.dto.ArchiveType;
import com.king.archive.factory.ArchiveServiceFactory;
import com.king.archive.service.IArchiveMgrService;
import com.king.framework.utils.CacheUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 档案缓存工具类
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
@Component
public class ArchiveCache {

    private static Logger logger = LoggerFactory.getLogger(ArchiveCache.class);

    private static final String ARCHIVE_CACHE_PREFIX = "archive_redis_cache:";
    private static final Integer ARCHIVE_CACHE_TIMEOUT = 7 * 24;
    private static final TimeUnit ARCHIVE_CACHE_TIMEOUT_UNIT = TimeUnit.HOURS;

//    @Value("${default.archive.local-cache.enable}")
    private static boolean enableLocalCache = true;//是否启用本地缓存，默认开启
//    @Value("${default.archive.redis-cache.enable}")
    private static boolean enableRedisCache = true;//是否启用redis缓存，默认开启
//    @Value("${default.archive.redis-cache.expire.hours}")
    private static Integer redisCacheExpireTime = 0;

    /* 档案类型缓存状态 */
    private static Map<ArchiveType,Boolean> cacheables = new ConcurrentHashMap<>();

    public static ArchiveType[] ranges = {
        ArchiveType.METER,
        ArchiveType.TERMINAL
    };

    /* ehcache本地缓存 */
    private static CacheManager localCacheManager;

    static {
        for(ArchiveType archiveType : ranges){
            regist(archiveType);
        }
    }

    /**
     * 注册档案类型
     * @param archiveType
     */
    public static void regist(ArchiveType archiveType) {
        cacheables.put(archiveType,true);
    }

    /**
     * 将档案对象放入缓存
     * @param dmo
     */
    public static void put(ArchiveDmo dmo){
        if(dmo != null){
            ArchiveType archiveType = dmo.getArchiveType();
            Long id = dmo.getId();
            if(archiveType != null && id != null){
                putInLocalStore(dmo);//缓存本地
                putInRedisStore(dmo);//缓存redis
            }
        }
    }

    /**
     * 获取档案数据.
     *
     * @param archType
     * @param id
     * @return
     */
    public static ArchiveDmo get(ArchiveType archType, Long id) {

        ArchiveDmo dmo = null;
        if (archType != null && id != null) {
            dmo = getFromLocalStore(archType, id);
            if (dmo == null) {
                dmo = getFromRedisStore(archType, id);
                if (dmo == null) {
                    IArchiveMgrService<ArchiveDto> service = ArchiveServiceFactory.getInstance().getService(archType);
                    assert( service != null);
                    ArchiveDto dto = service.get(id);
                    if (dto != null) {
                        dmo = service.getLazyLoader().getDmo(dto);
                        ArchiveCache.put(dmo);
                    } else {
                        storeUnExistArchive(archType, id);
                    }
                } else {
                    if (dmo instanceof NullDmo) {
                        dmo = null;
                    } else {
                        putInLocalStore(dmo);
                    }
                }
            }
        }
        return dmo;
    }

    /**
     * 移除档案数据.
     *
     * @param archType
     * @param id
     */
    public static void remove(ArchiveType archType, Long id) {

        if (archType != null && id != null) {
            removeFromLocalStore(archType, id);
            removeFromRedisStore(archType, id);
        }
    }

    /**
     * 移除本地档案数据.
     *
     * @param archType
     * @param id
     */
    private static void removeFromLocalStore(ArchiveType archType, Long id) {

        assert (archType != null && id != null);
        if (enableLocalCache()) {
            Cache cache = localCacheManager.getCache(archType.toString());
            if (cache != null) {
                cache.remove(id.toString());
            }
        }
    }

    /**
     * 移除远程档案数据.
     *
     * @param archType
     * @param id
     */
    private static void removeFromRedisStore(ArchiveType archType, Long id) {

        assert (archType != null && id != null);
        if (enableRedisCache()) {
            String cacheName = getCacheName(archType);
            CacheUtils.remove(cacheName, id.toString());
        }
    }

    /**
     *短暂缓存不存在的档案.
     * <p>
     *  防止redis穿透
     * </p>
     * @param archType
     * @param id
     */
    private static void storeUnExistArchive(ArchiveType archType, Long id) {

        if (enableRedisCache()) {
            String cacheName = getCacheName(archType);
            ArchiveDmo dmo = new NullDmo(archType);
            CacheUtils.put(cacheName, String.valueOf(id), dmo, 60,
                    TimeUnit.SECONDS);
        }
    }

    /**
     * 获取本地档案数据.
     *
     * @param archType
     * @param id
     * @return
     */
    private static ArchiveDmo getFromLocalStore(ArchiveType archType, Long id) {

        assert (archType != null && id != null);
        ArchiveDmo dmo = null;
        if (enableLocalCache()) {
            Cache cache = localCacheManager.getCache(archType.toString());
            if (cache != null) {
                Element element = cache.get(id.toString());
                if (element != null) {
                    dmo = (ArchiveDmo) element.getValue();
                }
            }
        }
        return dmo;
    }

    /**
     * 获取远程档案数据.
     *
     * @param archType
     * @param id
     * @return
     */
    private static ArchiveDmo getFromRedisStore(ArchiveType archType, Long id) {

        assert (archType != null && id != null);
        ArchiveDmo dmo = null;
        if (enableRedisCache()) {
            String cacheName = getCacheName(archType);
            try {
                dmo = (ArchiveDmo) CacheUtils.get(cacheName, id.toString());
            } catch (Exception e) {
                logger.warn("ArchiveCache.getFromRedisStore err:", e);
            }
        }
        return dmo;
    }

    /**
     * 本地缓存 ehcache
     * @param dmo
     */
    private static void putInLocalStore(ArchiveDmo dmo) {
        assert (dmo != null);
        ArchiveType archiveType = dmo.getArchiveType();
        assert (archiveType != null);
        if (enableLocalCache()) {
            Cache cache = localCacheManager.getCache(archiveType.toString());
            if (cache != null) {
                ArchiveDmo cloneDmo = null;
                try {
                    cloneDmo = (ArchiveDmo) dmo.clone();
                    cache.put(new Element(String.valueOf(dmo.getId()), cloneDmo));
                } catch (CloneNotSupportedException e) {
                    logger.warn("ArchiveCache.putInLocalStore err:", e);
                } finally {
                    cloneDmo = null;
                }
            }
        }
    }

    /**
     * 远程存储. redis
     *
     * @param dmo
     */
    static void putInRedisStore(ArchiveDmo dmo) {

        assert (dmo != null);
        ArchiveType archType = dmo.getArchiveType();
        assert (archType != null);
        if (enableRedisCache()) {
            String cacheName = getCacheName(archType);
            ArchiveDmo cloneDmo = null;
            try {
                cloneDmo = (ArchiveDmo) dmo.clone();
                CacheUtils.put(cacheName, String.valueOf(dmo.getId()), cloneDmo, getRedisCacheExpireHours(),
                        ARCHIVE_CACHE_TIMEOUT_UNIT);
            } catch (CloneNotSupportedException e) {
                logger.warn("ArchiveCache.putInRedisStore err:", e);
            } finally {
                cloneDmo = null;
            }
        }
    }

    /**
     * 获取redis缓存名.
     * @param archType
     * @return
     */
    private static String getCacheName(ArchiveType archType) {

        return ARCHIVE_CACHE_PREFIX + archType.getValue();
    }

    /**
     * 获取档案缓存失效时间.
     *
     * @return
     */
    private static Integer getRedisCacheExpireHours() {
        return redisCacheExpireTime > 0 ? redisCacheExpireTime : ARCHIVE_CACHE_TIMEOUT;
    }

    /**
     * 是否启用本地缓存.
     *
     * @return
     */
    static boolean enableLocalCache() {

        return enableLocalCache;
    }

    /**
     * 是否启用远程缓存.
     *
     * @return
     */
    static boolean enableRedisCache() {

        return enableRedisCache;
    }

    public static void setLocalCacheManager(CacheManager localCacheManager) {
        ArchiveCache.localCacheManager = localCacheManager;
    }

    /**
     * 档案是否允许缓存.
     * @param dto
     * @return
     */
    public static boolean isCacheable(ArchiveDto dto) {

        return (dto == null) ? false
                : isCacheable(dto.getArchiveType());
    }

    /**
     * 档案是否允许缓存.
     * @param archType
     * @return
     */
    public static boolean isCacheable(ArchiveType archType) {

        return (archType == null) ? false
                :cacheables.keySet().contains(archType);
    }

    /**
     * 获取已缓存档案类型.
     *
     * @return
     */
    public static Set<ArchiveType> getCacheables() {

        return cacheables.keySet();
    }
}
