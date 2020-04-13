package com.king.framework.exception;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
public class ParamException extends BaseException {
    private static final long serialVersionUID = 6152837173841931573L;

    public ParamException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    public ParamException(int errorCode, String errorMsg, Throwable cause) {
        super(errorCode, errorMsg, cause);
    }

    public ParamException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public ParamException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ParamException(int errorCode) {
        super(errorCode);
    }

    public ParamException(String errorMsg) {
        super(errorMsg);
    }
}

