package com.king.framework.cache.impl;

import com.king.framework.cache.AssetsCache;
import com.king.framework.cache.HDirection;
import com.king.framework.exception.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class NativeAssetsCache<K, V> implements AssetsCache<K, V> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static Map<String, Object> store = new ConcurrentHashMap();
    private Lock lock = new ReentrantLock();
    private String keyPrefix;

    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public NativeAssetsCache(String prefix) {
        this.keyPrefix = prefix;
    }

    public V get(K key) throws CacheException {
        this.logger.debug("Get object from memory, the key is [" + key + "]");

        try {
            if (key == null) {
                return null;
            } else {
                V value = (V)store.get(this.prefixedKey(key));
                return value;
            }
        } catch (Throwable var3) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.get:", var3);
        }
    }

    public V put(K key, V value) throws CacheException {
        this.logger.debug("Put object into redis, the key is [" + key + "]");

        try {
            store.put(this.prefixedKey(key), value);
            return value;
        } catch (Throwable var4) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.put:", var4);
        }
    }

    public V put(K key, V value, long timeout, TimeUnit unit) throws CacheException {
        this.logger.debug("Put object into redis, the key is [" + key + "]");

        try {
            store.put(this.prefixedKey(key), value);
            this.expire(key, timeout, unit);
            return value;
        } catch (Throwable var7) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.put:", var7);
        }
    }

    public V remove(K key) throws CacheException {
        this.logger.debug("Remove object from redis, the key is [" + key + "]");

        try {
            V previous = this.get(key);
            store.remove(this.prefixedKey(key));
            return previous;
        } catch (Throwable var3) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.remove:", var3);
        }
    }

    public void clear() throws CacheException {
        this.logger.debug("Remove all objects from redis");

        try {
            store.clear();
        } catch (Throwable var2) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.clear:", var2);
        }
    }

    public int size() {
        this.logger.debug("Get cache size from redis");

        try {
            return store.size();
        } catch (Throwable var2) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.size:", var2);
        }
    }

    public boolean exist(K key) {
        try {
            return store.containsKey(this.prefixedKey(key));
        } catch (Exception var3) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.exist:", var3);
        }
    }

    public Set<K> keys() {
        Set<K> keys = new HashSet();
        Iterator var2 = store.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var2.next();
            keys.add((K)entry.getKey());
        }

        return keys;
    }

    public Set<K> keys(String pattern) {
        try {
            String replacePattern = pattern.replaceAll("\\*", "");
            Set<K> keys = new HashSet();
            Iterator var4 = store.entrySet().iterator();

            while(var4.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var4.next();
                if (((String)entry.getKey()).startsWith(replacePattern)) {
                    keys.add((K)entry.getKey());
                }
            }

            return keys;
        } catch (Exception var6) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.keys:", var6);
        }
    }

    public long increment(K key) {
        this.lock.lock();

        long var3;
        try {
            Long inc = (Long)store.get(this.prefixedKey(key));
            store.put(this.prefixedKey(key), inc = inc + 1L);
            var3 = inc;
        } catch (Exception var8) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.increment:", var8);
        } finally {
            this.lock.unlock();
        }

        return var3;
    }

    public long decrement(K key) {
        this.lock.lock();

        long var3;
        try {
            Long dec = (Long)store.get(this.prefixedKey(key));
            store.put(this.prefixedKey(key), dec = dec - 1L);
            var3 = dec;
        } catch (Exception var8) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.decrement:", var8);
        } finally {
            this.lock.unlock();
        }

        return var3;
    }

    public void putList(K key, List<V> values) {
        try {
            this.remove(key);
            Iterator var3 = values.iterator();

            while(var3.hasNext()) {
                V value = (V)var3.next();
                this.putList(key, value, HDirection.RIGHT);
            }

        } catch (Exception var5) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.putList:", var5);
        }
    }

    public void putList(K key, List<V> values, long timeout, TimeUnit unit) {
        try {
            this.putList(key, values);
            this.expire(key, timeout, unit);
        } catch (Exception var7) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.putList:", var7);
        }
    }

    public Long putList(K key, V value, HDirection direction) {
        this.lock.lock();

        Long var6;
        try {
            List<Object> list = (List)store.get(this.prefixedKey(key));
            if (list == null) {
                list = new ArrayList();
            }

            int index = 0;
            if (direction == HDirection.LEFT) {
                ((List)list).add(0, value);
            } else {
                index = ((List)list).size();
                ((List)list).add(value);
            }

            store.put(this.prefixedKey(key), list);
            var6 = (long)index;
        } catch (Exception var10) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.putList:", var10);
        } finally {
            this.lock.unlock();
        }

        return var6;
    }

    public V popList(K key, HDirection direction) {
        this.lock.lock();

        Object var5;
        try {
            V value = null;
            List<Object> list = (List)store.get(this.prefixedKey(key));
            if (list != null && list.size() > 0) {
                if (direction == HDirection.LEFT) {
                    value = (V)list.get(0);
                } else {
                    value = (V)list.get(list.size() - 1);
                }
            }

            var5 = value;
        } catch (Exception var9) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.popList:", var9);
        } finally {
            this.lock.unlock();
        }

        return (V)var5;
    }

    public Long getListSize(K key) {
        try {
            List<Object> list = (List)store.get(this.prefixedKey(key));
            return list != null ? (long)list.size() : 0L;
        } catch (Exception var3) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.getListSize:", var3);
        }
    }

    public List<V> getList(K key, int start, int end) {
        try {
            List<V> list = new ArrayList();
            List<Object> objList = (List)store.get(this.prefixedKey(key));
            int i = 0;

            for(Iterator var7 = objList.iterator(); var7.hasNext(); ++i) {
                Object object = var7.next();
                if (i >= start && i < end) {
                    list.add((V)object);
                }
            }

            return list;
        } catch (Exception var9) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.getList:", var9);
        }
    }

    public void putSet(K key, Set<V> values, long timeout, TimeUnit unit) {
        try {
            this.putSet(key, values);
            this.expire(key, timeout, unit);
        } catch (Exception var7) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.putSet:", var7);
        }
    }

    public void putSet(K key, Set<V> values) {
        this.lock.lock();

        try {
            this.remove(key);
            Set<Object> set = (Set)store.get(this.prefixedKey(key));
            if (set == null) {
                set = new HashSet();
            }

            Iterator var4 = values.iterator();

            while(var4.hasNext()) {
                Object value = var4.next();
                ((Set)set).add(value);
            }

            store.put(this.prefixedKey(key), set);
        } catch (Exception var9) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.putSet:", var9);
        } finally {
            this.lock.unlock();
        }
    }

    public void putSet(K key, V value) {
        this.lock.lock();

        try {
            Set<Object> set = (Set)store.get(this.prefixedKey(key));
            if (set == null) {
                set = new HashSet();
            }

            ((Set)set).add(value);
            store.put(this.prefixedKey(key), set);
        } catch (Exception var7) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.putSet:", var7);
        } finally {
            this.lock.unlock();
        }

    }

    public Long getSetSize(K key) {
        try {
            Set<Object> set = (Set)store.get(this.prefixedKey(key));
            return set != null ? (long)set.size() : 0L;
        } catch (Exception var3) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.getSetSize:", var3);
        }
    }

    public Set<V> getSet(K key) {
        try {
            Set<V> set = new HashSet();
            Set<Object> objSet = (Set)store.get(this.prefixedKey(key));
            if (objSet != null) {
                Iterator var4 = objSet.iterator();

                while(var4.hasNext()) {
                    Object object = var4.next();
                    set.add((V)object);
                }
            }

            return set;
        } catch (Exception var6) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.getSet:", var6);
        }
    }

    public boolean isSetMember(K key, V value) {
        try {
            Set<Object> set = (Set)store.get(this.prefixedKey(key));
            if (set != null) {
                Iterator var4 = set.iterator();

                while(var4.hasNext()) {
                    Object obj = var4.next();
                    if (value.equals(obj)) {
                        return true;
                    }
                }
            }

            return false;
        } catch (Exception var6) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.isSetMember:", var6);
        }
    }

    public void putZset(K key, Set<V> values, long timeout, TimeUnit unit) {
        try {
            this.putZset(key, values);
            this.expire(key, timeout, unit);
        } catch (Exception var7) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.putZset:", var7);
        }
    }

    public void putZset(K key, Set<V> values) {
        this.lock.lock();

        try {
            this.remove(key);
            Set<Object> set = (Set)store.get(this.prefixedKey(key));
            if (set == null) {
                set = new TreeSet();
            }

            Iterator var4 = values.iterator();

            while(var4.hasNext()) {
                Object value = var4.next();
                ((Set)set).add(value);
            }

            store.put(this.prefixedKey(key), set);
        } catch (Exception var9) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.putZset:", var9);
        } finally {
            this.lock.unlock();
        }
    }

    public void putZset(K key, V value) {
        this.lock.lock();

        try {
            Set<Object> set = (Set)store.get(this.prefixedKey(key));
            if (set == null) {
                set = new TreeSet();
            }

            ((Set)set).add(value);
            store.put(this.prefixedKey(key), set);
        } catch (Exception var7) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.putZset:", var7);
        } finally {
            this.lock.unlock();
        }

    }

    public Long getZsetSize(K key) {
        try {
            Set<Object> set = (Set)store.get(this.prefixedKey(key));
            return set != null ? (long)set.size() : 0L;
        } catch (Exception var3) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.getZsetSize:", var3);
        }
    }

    public Set<V> getZset(K key, int start, int end) {
        try {
            Set<V> set = new HashSet();
            Set<Object> objSet = (Set)store.get(this.prefixedKey(key));
            Iterator var6 = objSet.iterator();

            while(var6.hasNext()) {
                Object object = var6.next();
                set.add((V)object);
            }

            return set;
        } catch (Exception var8) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.getZset:", var8);
        }
    }

    public void putMap(K key, String mapKey, V mapValue) {
        this.lock.lock();

        try {
            Map<String, Object> map = (Map)store.get(this.prefixedKey(key));
            if (map == null) {
                map = new HashMap();
            }

            ((Map)map).put(mapKey, mapValue);
            store.put(this.prefixedKey(key), map);
        } catch (Exception var8) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.putMap:", var8);
        } finally {
            this.lock.unlock();
        }

    }

    public void delMap(K key, String mapKey) {
        this.lock.lock();

        try {
            Map<String, Object> map = (Map)store.get(this.prefixedKey(key));
            if (map != null) {
                map.remove(mapKey);
            }
        } catch (Exception var7) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.delMap:", var7);
        } finally {
            this.lock.unlock();
        }

    }

    public V getMap(K key, String mapKey) {
        try {
            V value = null;
            Map<String, V> map = (Map)store.get(this.prefixedKey(key));
            if (map != null) {
                value = map.get(mapKey);
            }

            return value;
        } catch (Exception var5) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.getMap:", var5);
        }
    }

    public List<V> getMapValues(K key) {
        try {
            List<V> list = new ArrayList();
            Map<String, Object> map = (Map)store.get(this.prefixedKey(key));
            if (map != null) {
                Iterator var4 = map.entrySet().iterator();

                while(var4.hasNext()) {
                    Map.Entry<String, V> entry = (Map.Entry)var4.next();
                    list.add(entry.getValue());
                }
            }

            return list;
        } catch (Exception var6) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.getMapValues:", var6);
        }
    }

    public Long getMapSize(K key) {
        try {
            Map<String, Object> map = (Map)store.get(this.prefixedKey(key));
            return map != null ? (long)map.size() : 0L;
        } catch (Exception var3) {
            throw new CacheException("com.smsc.framework.cache.impl.NativeAssetsCache.getMapSize:", var3);
        }
    }

    private String prefixedKey(K key) {
        return this.keyPrefix + ":" + key;
    }

    private void expire(final K key, long timeout, TimeUnit unit) {
        if (store.containsKey(this.prefixedKey(key))) {
            (new Timer()).schedule(new TimerTask() {
                public void run() {
                    NativeAssetsCache.store.remove(NativeAssetsCache.this.prefixedKey(key));
                }
            }, TimeUnit.MILLISECONDS.convert(timeout, unit));
        }

    }
}
