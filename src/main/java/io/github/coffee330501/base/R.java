package io.github.coffee330501.base;

import java.io.Serializable;

public class R implements Serializable {
    private final Integer code;
    private final String msg;
    private final Object data;

    public R(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R err(String msg) {
        return new R(500, msg, null);
    }

    public static <T> R ok(T data) {
        return new R(200, null, data);
    }

    public static <T> R ok() {
        return ok(null);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
