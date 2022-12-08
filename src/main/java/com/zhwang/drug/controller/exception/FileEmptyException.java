package com.zhwang.drug.controller.exception;

/**
 * 未选择文件或文件为空异常
 */
public class FileEmptyException extends FileUploadException {

    private static final long serialVersionUID = 5216328143694529891L;

    public FileEmptyException() {
        super();
    }

    public FileEmptyException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public FileEmptyException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FileEmptyException(String arg0) {
        super(arg0);
    }

    public FileEmptyException(Throwable arg0) {
        super(arg0);
    }

}
