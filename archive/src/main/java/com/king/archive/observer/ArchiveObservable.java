package com.king.archive.observer;

import com.king.framework.observer.AbstractObservable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * 档案观察者实现类
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
@Component
public class ArchiveObservable extends AbstractObservable {

    @Autowired
    private List<ArchiveObserver> archiveObservers;

    @PostConstruct
    @Override
    public void init(){
        if(archiveObservers != null && archiveObservers.size() > 0){
            List<Observer> observers = new ArrayList<>();
            for(ArchiveObserver ob : archiveObservers){
                observers.add(ob);
            }
            super.setObservers(observers);
        }
        super.init();
    }

    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }
}
