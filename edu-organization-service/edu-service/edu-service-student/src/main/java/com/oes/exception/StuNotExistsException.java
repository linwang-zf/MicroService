package com.oes.exception;

import com.oes.Exceptions.base.BaseException;

/**
 * @author : JQK
 * @date : 2020-06-30 15:29
 * @description : 学生不存在异常
 */
public class StuNotExistsException extends BaseException {

    private static final String module = "user.not.exists";

    public StuNotExistsException(final String message) {
        super(module, message);
    }

    public StuNotExistsException(Throwable cause) {
        super(cause);
    }

    public StuNotExistsException(String module, String message) {
        super(module, message);
    }

    public StuNotExistsException(String message, Throwable cause) {
        super(module, message, cause);
    }

    public StuNotExistsException(String module, String message, Throwable cause) {
        super(module, message, cause);
    }

}
