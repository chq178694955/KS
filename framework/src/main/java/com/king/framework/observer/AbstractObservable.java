package com.king.framework.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
public abstract class AbstractObservable extends Observable {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<Observer> observers;

    public AbstractObservable() {
    }

    public void init() {
        if (this.observers != null && this.observers.size() > 0) {
            Iterator iterObservers = this.observers.iterator();

            while(iterObservers.hasNext()) {
                this.addObserver((Observer)iterObservers.next());
            }
        }

    }

    public List<Observer> getObservers() {
        return this.observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }
}

