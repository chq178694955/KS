package com.king.framework.lock.impl;

import com.king.framework.lock.ILock;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class NativeLockImpl implements ILock {
    private Lock lock;
    private String lockKey;
    private int expireMsecs;
    private int timeoutMsecs;
    private volatile boolean locked;

    public NativeLockImpl(String lockKey) {
        this.lock = new ReentrantLock();
        this.expireMsecs = 60000;
        this.timeoutMsecs = 10000;
        this.locked = false;
        this.lockKey = "LOCK_" + lockKey;
    }

    public NativeLockImpl(String lockKey, int timeoutMsecs) {
        this(lockKey);
        this.timeoutMsecs = timeoutMsecs;
    }

    public NativeLockImpl(String lockKey, int timeoutMsecs, int expireMsecs) {
        this(lockKey, timeoutMsecs);
        this.expireMsecs = expireMsecs;
    }

    public boolean lock() {
        try {
            boolean var1 = this.lock.tryLock((long)this.timeoutMsecs, TimeUnit.MILLISECONDS);
            return var1;
        } catch (InterruptedException var5) {
            var5.printStackTrace();
        } finally {
            class NamelessClass_1 extends TimerTask {
                NamelessClass_1() {
                }

                public void run() {
                    NativeLockImpl.this.unlock();
                }
            }

            (new Timer()).schedule(new NamelessClass_1(), (long)this.expireMsecs);
        }

        return false;
    }

    public void unlock() {
        this.lock.unlock();
        this.locked = false;
    }

    public String getKey() {
        return this.lockKey;
    }

    public boolean isLocked() {
        return this.locked;
    }
}
