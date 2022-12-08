package com.zhwang.drug.service.exception;

/**
 * 客户时间异常，图表展示
 */
public class YearMonthException extends ServiceException {
    private static final long serialVersionUID = 1L;

    public YearMonthException() {
        super();
    }

    public YearMonthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public YearMonthException(String message, Throwable cause) {
        super(message, cause);
    }

    public YearMonthException(String message) {
        super(message);
    }

    public YearMonthException(Throwable cause) {
        super(cause);
    }

}
