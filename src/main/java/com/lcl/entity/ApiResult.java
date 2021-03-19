package com.lcl.entity;

import java.io.Serializable;
import java.util.Collections;

public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -3540272309116909111L;
    protected int code;
    protected String message;
    protected T zpData;
    public static final ApiResult FAIL;

    public ApiResult() {
        this.zpData = (T) Collections.EMPTY_MAP;
    }

    public ApiResult(int code, String message, T zpData) {
        this.zpData = (T) Collections.EMPTY_MAP;
        this.code = code;
        this.message = message;
        this.zpData = zpData;
    }

    private static <T> ApiResult<T> wrap(int code, String msg, T data) {
        return new ApiResult(code, msg, data);
    }

    public static <T> ApiResult<T> success(T data) {
        return wrap(ResultCode.SUCC.getCode(), ResultCode.SUCC.getMsg(), data);
    }

    public static <T> ApiResult<T> failed(int code, String msg) {
        return (ApiResult<T>) wrap(code, msg, Collections.EMPTY_MAP);
    }

    public static <T> ApiResult<T> failed(String msg) {
        return failed(ResultCode.FAIL.getCode(), msg);
    }

    public static <T> ApiResult<T> failed(ResultCode resultCode) {
        return failed(resultCode.getCode(), resultCode.getMsg());
    }

    public static <T> ApiResult<T> failed(ResultCode resultCode, String message) {
        return failed(resultCode.getCode(), message);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getZpData() {
        return this.zpData == null ? (T) Collections.EMPTY_MAP : this.zpData;
    }

    public void setZpData(T zpData) {
        this.zpData = zpData;
    }

    public String toString() {
        return "ApiResult{code=" + this.code + ", message='" + this.message + '\'' + ", zpData=" + this.zpData + '}';
    }

    static {
        FAIL = failed(ResultCode.FAIL);
    }
}
