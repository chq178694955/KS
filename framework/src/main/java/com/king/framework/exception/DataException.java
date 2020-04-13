package com.king.framework.exception;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
public class DataException extends BaseException {
    private static final long serialVersionUID = -7036615893897531900L;

    public DataException(int errorCode, String errorMsg, Throwable cause) {
        super(errorCode, errorMsg, cause);
    }

    public DataException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public DataException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public DataException(int errorCode) {
        super(errorCode);
    }

    public DataException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    public DataException(String errorMsg) {
        super(errorMsg);
    }
}

