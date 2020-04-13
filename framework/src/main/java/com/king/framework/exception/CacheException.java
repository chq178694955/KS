package com.king.framework.exception;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
public class CacheException extends BaseException {
    private static final long serialVersionUID = -4707433223701720434L;

    public CacheException(int errorCode, String errorMsg, Throwable cause) {
        super(errorCode, errorMsg, cause);
    }

    public CacheException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public CacheException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public CacheException(int errorCode) {
        super(errorCode);
    }

    public CacheException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    public CacheException(String errorMsg) {
        super(errorMsg);
    }
}
