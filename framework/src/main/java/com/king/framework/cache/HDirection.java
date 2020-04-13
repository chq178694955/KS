package com.king.framework.cache;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public enum HDirection {
    LEFT(-1),
    RIGHT(1);

    private int value;

    private HDirection(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static HDirection fromValue(int value) {
        HDirection ret = null;
        HDirection[] var2 = values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            HDirection em = var2[var4];
            if (em.getValue() == value) {
                ret = em;
                break;
            }
        }

        return ret;
    }
}
