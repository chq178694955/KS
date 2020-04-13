package com.king.framework.cache;

import com.king.framework.exception.CacheException;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public interface AssetsCache<K, V> {
    V get(K var1) throws CacheException;

    V put(K var1, V var2) throws CacheException;

    V put(K var1, V var2, long var3, TimeUnit var5) throws CacheException;

    V remove(K var1) throws CacheException;

    void clear() throws CacheException;

    int size();

    boolean exist(K var1);

    Set<K> keys();

    Set<K> keys(String var1);

    long increment(K var1);

    long decrement(K var1);

    void putList(K var1, List<V> var2);

    void putList(K var1, List<V> var2, long var3, TimeUnit var5);

    Long putList(K var1, V var2, HDirection var3);

    V popList(K var1, HDirection var2);

    List<V> getList(K var1, int var2, int var3);

    Long getListSize(K var1);

    void putSet(K var1, Set<V> var2, long var3, TimeUnit var5);

    void putSet(K var1, Set<V> var2);

    void putSet(K var1, V var2);

    Set<V> getSet(K var1);

    boolean isSetMember(K var1, V var2);

    Long getSetSize(K var1);

    void putZset(K var1, Set<V> var2, long var3, TimeUnit var5);

    void putZset(K var1, Set<V> var2);

    void putZset(K var1, V var2);

    Set<V> getZset(K var1, int var2, int var3);

    Long getZsetSize(K var1);

    void putMap(K var1, String var2, V var3);

    void delMap(K var1, String var2);

    V getMap(K var1, String var2);

    List<V> getMapValues(K var1);

    Long getMapSize(K var1);
}
