package com.king.framework.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class Criteria extends HashMap<String, Object> implements Cloneable {
    private static final long serialVersionUID = -5931004407909005934L;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Criteria() {
    }

    public Object clone() {
        return super.clone();
    }

    public synchronized void setAttribute(String name, Object value) {
        this.put(name, value);
    }

    public String getStringValue(String name) {
        Object obj = this.get(name);
        if (obj != null) {
            try {
                return String.valueOf(obj);
            } catch (Exception var4) {
                this.logger.error("Fail to parse [" + obj + "] to type of String!" + var4);
                return null;
            }
        } else {
            return null;
        }
    }

    public Integer getIntegerValue(String name) {
        Object obj = this.get(name);
        if (obj != null) {
            try {
                return Integer.parseInt(String.valueOf(obj));
            } catch (Exception var4) {
                this.logger.error("Fail to parse [" + obj + "] to type of Integer!" + var4);
                return null;
            }
        } else {
            return null;
        }
    }

    public Long getLongValue(String name) {
        Object obj = this.get(name);
        if (obj != null) {
            try {
                return Long.valueOf(String.valueOf(obj));
            } catch (Exception var4) {
                this.logger.error("Fail to parse [" + obj + "] to type of Long!" + var4);
                return null;
            }
        } else {
            return null;
        }
    }

    public Float getFloatValue(String name) {
        Object obj = this.get(name);
        if (obj != null) {
            try {
                return Float.valueOf(String.valueOf(obj));
            } catch (Exception var4) {
                this.logger.error("Fail to parse [" + obj + "] to type of Float!" + var4);
                return null;
            }
        } else {
            return null;
        }
    }

    public Double getDoubleValue(String name) {
        Object obj = this.get(name);
        if (obj != null) {
            try {
                return Double.valueOf(String.valueOf(obj));
            } catch (Exception var4) {
                this.logger.error("Fail to parse [" + obj + "] to type of Double!" + var4);
                return null;
            }
        } else {
            return null;
        }
    }

    public Boolean getBooleanValue(String name) {
        Object obj = this.get(name);
        if (obj != null) {
            try {
                return Boolean.valueOf(String.valueOf(obj));
            } catch (Exception var4) {
                this.logger.error("Fail to parse [" + obj + "] to type of Boolean!" + var4);
                return null;
            }
        } else {
            return null;
        }
    }
}
