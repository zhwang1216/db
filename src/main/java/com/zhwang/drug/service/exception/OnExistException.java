package com.zhwang.drug.service.exception;

/**
 * 进货,异常,入库单据号已存在
 */
public class OnExistException extends ServiceException {


    private static final long serialVersionUID = 1L;

    public OnExistException() {
        super();
    }

    public OnExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OnExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnExistException(String message) {
        super(message);
    }

    public OnExistException(Throwable cause) {
        super(cause);
    }

}
