package com.oes.Exceptions.organization;

import com.oes.Exceptions.base.BaseException;

/**
 * @author : JQK
 * @date : 2020-06-29 10:26
 * @description : 机构不存在异常
 */
public class OrgNotExistsException extends BaseException {

    private static final String module = "org.not.exists";

    public OrgNotExistsException(Throwable cause) {
        super(cause);
    }

    public OrgNotExistsException(final String message) {
        super(module, message);
    }

    public OrgNotExistsException(String module, String message) {
        super(module, message);
    }

    public OrgNotExistsException(String message, Throwable cause) {
        super(module, message, cause);
    }

    public OrgNotExistsException(String module, String message, Throwable cause) {
        super(module, message, cause);
    }

}
