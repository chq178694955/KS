package com.king.framework.exception;

/**
 * @创建人 chq
 * @创建时间 2020/3/18
 * @描述
 */
public class BusinessException extends BaseException {
    private static final long serialVersionUID = 4167383429267581948L;

    public BusinessException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    public BusinessException(int errorCode, String errorMsg, Throwable cause) {
        super(errorCode, errorMsg, cause);
    }

    public BusinessException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public BusinessException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public BusinessException(int errorCode) {
        super(errorCode);
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
    }
}

