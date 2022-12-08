package com.zhwang.drug.service.exception;

/**
 * 外键引用异常
 */
public class ForeignKeyReferenceException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public ForeignKeyReferenceException() {
        super();
    }

    public ForeignKeyReferenceException(String message, Throwable cause, boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ForeignKeyReferenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForeignKeyReferenceException(String message) {
        super(message);
    }

    public ForeignKeyReferenceException(Throwable cause) {
        super(cause);
    }

}
