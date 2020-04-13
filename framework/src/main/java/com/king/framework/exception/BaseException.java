package com.king.framework.exception;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 6977348799147153074L;
    private int errorCode;
    protected Throwable cause;

    public BaseException(int errorCode) {
        this.errorCode = errorCode;
    }

    public BaseException(String errorMsg) {
        super(errorMsg);
    }

    public BaseException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public BaseException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.cause = cause;
    }

    public BaseException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.cause = cause;
    }

    public BaseException(int errorCode, Throwable cause) {
        super(cause);
        this.cause = cause;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
