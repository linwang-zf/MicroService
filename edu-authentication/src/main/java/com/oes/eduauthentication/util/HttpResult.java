package com.oes.eduauthentication.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : yanjundong
 * @date : 2020-03-26 17:02
 * @description : HTTP结果封装
 */

@Getter
@Setter
@ToString
public class HttpResult {

    private int code = 200;
    private String msg;
    private Object data;

    public static HttpResult ok(String msg) {
        HttpResult result = new HttpResult();
        result.setMsg(msg);
        return result;
    }

    public static HttpResult ok(Object data) {
        HttpResult result = new HttpResult();
        result.setData(data);
        return result;
    }

    public static HttpResult ok(String msg, Object data) {
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

    public static HttpResult error(String msg,Object data) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg, data);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static HttpResult error(int code, String msg,Object data) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


}
