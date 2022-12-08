package com.zhwang.drug.controller.exception;

/**
 * 文件大小异常
 */
public class FileSizeException extends FileUploadException {


    private static final long serialVersionUID = 3652563516851916279L;

    public FileSizeException() {
        super();
    }

    public FileSizeException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public FileSizeException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FileSizeException(String arg0) {
        super(arg0);
    }

    public FileSizeException(Throwable arg0) {
        super(arg0);
    }

}
