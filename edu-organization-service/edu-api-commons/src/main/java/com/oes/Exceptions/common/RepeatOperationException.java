package com.oes.Exceptions.common;

import com.oes.Exceptions.base.BaseException;

/**
 * @author : JQK
 * @date : 2020-08-26 20:54
 * @description : 业务重复操作异常
 */
public class RepeatOperationException extends BaseException {

    private static final String module = "cannot repeat operation";

    public RepeatOperationException(Throwable cause) {
        super(cause);
    }

    public RepeatOperationException(final String message) {
        super(module, message);
    }

    public RepeatOperationException(String module, String message) {
        super(module, message);
    }

    public RepeatOperationException(String message, Throwable cause) {
        super(module, message, cause);
    }

    public RepeatOperationException(String module, String message, Throwable cause) {
        super(module, message, cause);
    }

}
