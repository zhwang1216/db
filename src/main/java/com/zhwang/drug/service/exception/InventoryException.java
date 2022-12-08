package com.zhwang.drug.service.exception;

/**
 * 修改异常，修改库存失败
 */
public class InventoryException extends ServiceException {
    private static final long serialVersionUID = 1L;

    public InventoryException() {
        super();
    }

    public InventoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryException(String message) {
        super(message);
    }

    public InventoryException(Throwable cause) {
        super(cause);
    }

}
