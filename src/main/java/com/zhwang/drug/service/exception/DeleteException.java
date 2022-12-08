package com.zhwang.drug.service.exception;

/**
 * 删除异常，如果返回行数不是1，则抛出该异常
 */
public class DeleteException extends ServiceException {
    private static final long serialVersionUID = 1L;

    public DeleteException() {
        super();
    }

    public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

}
