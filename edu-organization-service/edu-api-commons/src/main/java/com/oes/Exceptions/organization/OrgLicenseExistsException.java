package com.oes.Exceptions.organization;

import com.oes.Exceptions.base.BaseException;

/**
 * @author : JQK
 * @date : 2020-07-25 22:14
 * @description : 机构license不唯一
 */
public class OrgLicenseExistsException extends BaseException {

    private static final String module = "org.license.exist";

    public OrgLicenseExistsException(Throwable cause) {
        super(cause);
    }

    public OrgLicenseExistsException(final String message) {
        super(module, message);
    }

    public OrgLicenseExistsException(String module, String message) {
        super(module, message);
    }

    public OrgLicenseExistsException(String message, Throwable cause) {
        super(module, message, cause);
    }

    public OrgLicenseExistsException(String module, String message, Throwable cause) {
        super(module, message, cause);
    }

}
