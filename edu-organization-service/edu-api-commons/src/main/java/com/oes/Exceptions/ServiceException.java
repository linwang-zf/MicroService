package com.oes.Exceptions;

import com.oes.Exceptions.base.BaseException;

/**
 * @author : yanjundong
 * @date : 2020-04-19 11:31
 * @description :
 */

public class ServiceException extends BaseException {

    private static final String module = "service.error";

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(final String message) {
        super(module, message);
    }

    public ServiceException(final String module, final String message) {
        super(module, message);
    }

    public ServiceException(final String message, Throwable cause) {
        super(module, message, cause);
    }

    public ServiceException(final String module, final String message, Throwable cause) {
        super(module, message, cause);
    }

}
