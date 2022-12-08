package com.zhwang.drug.controller.exception;

/**
 * 上传文件时异常的基类
 */
public class FileUploadException extends RuntimeException {


    private static final long serialVersionUID = -4816421833308748249L;

    public FileUploadException() {
        super();
    }

    public FileUploadException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public FileUploadException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FileUploadException(String arg0) {
        super(arg0);
    }

    public FileUploadException(Throwable arg0) {
        super(arg0);
    }

}
