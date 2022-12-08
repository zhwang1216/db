package com.zhwang.drug.service.exception;

/**
 * 药品类别名称重复异常
 */
public class CategoryNameDuplicateException extends ServiceException {
    private static final long serialVersionUID = 1L;

    public CategoryNameDuplicateException() {
        super();
    }

    public CategoryNameDuplicateException(String message, Throwable cause, boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CategoryNameDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryNameDuplicateException(String message) {
        super(message);
    }

    public CategoryNameDuplicateException(Throwable cause) {
        super(cause);
    }

}
