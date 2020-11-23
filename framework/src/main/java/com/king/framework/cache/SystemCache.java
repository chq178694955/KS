package com.king.framework.cache;

import java.util.TimerTask;

/**
 * @创建人 chq
 * @创建时间 2020/3/14
 * @描述
 */
public abstract class SystemCache extends TimerTask {

    @Override
    public void run() {
        init();
    }

    public void refresh(){
        init();
    }

    protected abstract void init();

}
