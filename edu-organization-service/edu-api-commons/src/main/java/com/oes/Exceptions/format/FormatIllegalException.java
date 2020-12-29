package com.oes.Exceptions.format;

import com.oes.Exceptions.base.BaseException;

/**
 * @author : JQK
 * @date : 2020-07-21 9:29
 * @description : 数据格式不符合要求
 */
public class FormatIllegalException extends BaseException {

    private static final String module = "format.illegal";

    public FormatIllegalException(Throwable cause) {
        super(cause);
    }

    public FormatIllegalException(final String message) {
        super(module, message);
    }

    public FormatIllegalException(String module, String message) {
        super(module, message);
    }

    public FormatIllegalException(String message, Throwable cause) {
        super(module, message, cause);
    }

    public FormatIllegalException(String module, String message, Throwable cause) {
        super(module, message, cause);
    }

}
