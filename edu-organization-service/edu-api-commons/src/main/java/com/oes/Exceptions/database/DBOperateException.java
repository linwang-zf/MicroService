package com.oes.Exceptions.database;

import com.oes.Exceptions.base.BaseException;

/**
 * @author : JQK
 * @date : 2020-06-28 21:33
 * @description : 数据库操作异常
 */
public class DBOperateException extends BaseException {
    private static final long serialVersionUID = -1978026315767612518L;

    private static final String module = "database.operation";


    public DBOperateException(Throwable cause) {
        super(cause);
    }

    public DBOperateException(final String message) {
        super(module, message);
    }

    public DBOperateException(String module, String message) {
        super(module, message);
    }

    public DBOperateException(String message, Throwable cause) {
        super(module, message, cause);
    }

    public DBOperateException(String module, String message, Throwable cause) {
        super(module, message, cause);
    }
}
