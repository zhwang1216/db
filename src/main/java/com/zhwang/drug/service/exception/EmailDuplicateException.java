package com.zhwang.drug.service.exception;

/**
 * 邮箱被占用异常
 */
public class EmailDuplicateException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public EmailDuplicateException() {
        super();
    }

    public EmailDuplicateException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EmailDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailDuplicateException(String message) {
        super(message);
    }

    public EmailDuplicateException(Throwable cause) {
        super(cause);
    }

}
