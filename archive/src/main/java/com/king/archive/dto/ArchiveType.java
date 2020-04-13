package com.king.archive.dto;

import com.king.framework.utils.I18nUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 chq
 * @创建时间 2020/3/19
 * @描述
 */
public enum ArchiveType implements CodeBaseEnum{

    METER(2001),
    TERMINAL(2010),
    UNKONW(-999999999);

    private int value;

    private ArchiveType(int value){
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ArchiveType fromValue(int value) {

        ArchiveType ret = null;
        for (ArchiveType em : ArchiveType.values()) {
            if (em.getValue() == value) {
                ret = em;
                break;
            }
        }
        return ret;
    }

    private static Map<String,String> tables = new HashMap<String,String>();

    public String toResourceName() {

        String key = String.format("%s.%s", new Object[] { this.getClass().getSimpleName(), this.name() });
        String value = tables.get(key);
        if (value == null) {
            value = I18nUtils.get(key);
            tables.put(key, value);
        }
        return value;//I18nUtils.getMessage(key);
    }

    @Override
    public String toString() {

        return String.valueOf(this.value);
    }
}
