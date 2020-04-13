package com.king.archive.observer;

import java.util.Observer;

/**
 * 档案信息观察者
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public interface ArchiveObserver extends Observer {

    /**
     * 是否通过，不做业务处理
     * @param arg
     * @return
     */
    public boolean pass(Object arg);

}
