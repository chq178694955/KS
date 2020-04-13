package com.king.framework.lock;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public interface ILock {
    boolean lock();

    void unlock();

    String getKey();

    boolean isLocked();
}
