package com.king.archive.dto;

/**
 * @创建人 chq
 * @创建时间 2020/3/19
 * @描述
 */
public enum HandlerType implements CodeBaseEnum{

    INSERT(0),

    UPDATE(1),

    DELETE(2);

    private int value;

    private HandlerType(int value) {

        this.value = value;
    }

    @Override
    public int getValue() {

        return value;
    }

    public void setValue(int value) {

        this.value = value;
    }

    public static HandlerType fromValue(int value) {

        HandlerType ret = null;
        for (HandlerType em : HandlerType.values()) {
            if (em.getValue() == value) {
                ret = em;
                break;
            }
        }
        return ret;
    }

}
