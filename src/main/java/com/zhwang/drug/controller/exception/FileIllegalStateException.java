package com.zhwang.drug.controller.exception;

/**
 * 文件状态异常
 */
public class FileIllegalStateException extends FileUploadException {

    private static final long serialVersionUID = 2633371394657028484L;

    public FileIllegalStateException() {
        super();
    }

    public FileIllegalStateException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public FileIllegalStateException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FileIllegalStateException(String arg0) {
        super(arg0);
    }

    public FileIllegalStateException(Throwable arg0) {
        super(arg0);
    }


}
