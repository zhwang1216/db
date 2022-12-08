package com.zhwang.drug.service.exception;

/**
 * 员工权限不足异常
 */
public class PermissionsInsufficientException extends ServiceException {

    private static final long serialVersionUID = -2896700230213037907L;

    public PermissionsInsufficientException() {
        super();
    }

    public PermissionsInsufficientException(String message, Throwable cause, boolean enableSuppression,
                                            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PermissionsInsufficientException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionsInsufficientException(String message) {
        super(message);
    }

    public PermissionsInsufficientException(Throwable cause) {
        super(cause);
    }


}
