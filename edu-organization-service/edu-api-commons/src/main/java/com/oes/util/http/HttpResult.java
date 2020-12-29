package com.oes.util.http;

import lombok.*;

@Getter
@Setter
@ToString
public class HttpResult<T> {
    private int code = 200;
    private String msg;
    private T data;

    public static HttpResult ok(String msg) {
        HttpResult result = new HttpResult();
        result.setMsg(msg);
        return result;
    }

    public static <T> HttpResult ok(T data) {
        HttpResult result = new HttpResult();
        result.setData(data);
        return result;
    }

    public static <T> HttpResult ok(String msg, T data) {
        HttpResult result = new HttpResult();
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static HttpResult error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static HttpResult error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static <T> HttpResult error(String msg, T data) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg, data);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> HttpResult error(int code, String msg, T data) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
