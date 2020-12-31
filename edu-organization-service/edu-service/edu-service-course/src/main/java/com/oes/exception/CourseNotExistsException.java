package com.oes.exception;

import com.oes.Exceptions.base.BaseException;

/**
 * @author : JQK
 * @date : 2020-06-30 15:29
 * @description : 课程不存在异常
 */
public class CourseNotExistsException extends BaseException {

    private static final String module = "course.not.exists";

    public CourseNotExistsException(Throwable cause) {
        super(cause);
    }

    public CourseNotExistsException(final String message) {
        super(module, message);
    }

    public CourseNotExistsException(String module, String message) {
        super(module, message);
    }

    public CourseNotExistsException(String message, Throwable cause) {
        super(module, message, cause);
    }

    public CourseNotExistsException(String module, String message, Throwable cause) {
        super(module, message, cause);
    }

}
