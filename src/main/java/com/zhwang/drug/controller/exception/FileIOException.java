package com.zhwang.drug.controller.exception;

/**
 * 文件读写异常
 */
public class FileIOException extends FileUploadException {

    private static final long serialVersionUID = -5303896993031321082L;

    public FileIOException() {
        super();
    }

    public FileIOException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public FileIOException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FileIOException(String arg0) {
        super(arg0);
    }

    public FileIOException(Throwable arg0) {
        super(arg0);
    }


}
