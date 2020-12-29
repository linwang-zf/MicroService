package com.oes.Exceptions.base;

/**
 * @author : JQK
 * @date : 2020-06-28 11:19
 * @description : 自定义基础异常
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 5731346544038378458L;

    /**
     * 所属模块
     */
    private final String module;

    public BaseException() {
        super();
        module = null;
    }

    public BaseException(Throwable cause) {
        super(cause);
        this.module = null;
    }

    public BaseException(final String module, final String message) {
        super(message);
        this.module = module;
    }

    public BaseException(String message, Throwable cause) {
        this(null, message, cause);
    }

    public BaseException(String module, String message, Throwable cause) {
        super(message, cause);
        this.module = module;
    }

    public String getModule() {
        return module;
    }

}
