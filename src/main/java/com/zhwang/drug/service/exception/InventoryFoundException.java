package com.zhwang.drug.service.exception;

/**
 * 库存不足
 */
public class InventoryFoundException extends ServiceException {

    private static final long serialVersionUID = 1L;

    public InventoryFoundException() {
        super();
    }

    public InventoryFoundException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InventoryFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryFoundException(String message) {
        super(message);
    }

    public InventoryFoundException(Throwable cause) {
        super(cause);
    }

}
