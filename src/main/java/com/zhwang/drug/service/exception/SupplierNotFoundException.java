package com.zhwang.drug.service.exception;

/**
 * 供应商不存在异常
 */
public class SupplierNotFoundException extends ServiceException {

    private static final long serialVersionUID = 8652404488225989300L;

    public SupplierNotFoundException() {
        super();
    }

    public SupplierNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SupplierNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SupplierNotFoundException(String message) {
        super(message);
    }

    public SupplierNotFoundException(Throwable cause) {
        super(cause);
    }


}
