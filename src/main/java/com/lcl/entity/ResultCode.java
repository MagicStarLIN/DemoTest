package com.lcl.entity;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.io.Serializable;

public class ResultCode implements Serializable {
    private static final long serialVersionUID = 8460819490759366955L;
    public static final ResultCode SUCC = new ResultCode(0, "Success");
    public static final ResultCode FAIL = new ResultCode(1, "系统服务错误");
    public static final ResultCode INVALID_PARAM = new ResultCode(2, "未知的非法参数");
    public static final ResultCode PERMISSION_DENY = new ResultCode(3, "用户权限限制");
    public static final ResultCode REQUEST_FREQ = new ResultCode(4, "请求过于频繁，请稍后再试");
    public static final ResultCode ANTISPAM = new ResultCode(5, "请求不合法(5)");
    public static final ResultCode INVALID_APP_ID = new ResultCode(6, "请求不合法(6)");
    public static final ResultCode INVALID_TICKET = new ResultCode(7, "当前登录状态已失效");
    public static final ResultCode INVALID_SIG = new ResultCode(8, "无效的签名");
    public static final ResultCode INVALID_VERSION = new ResultCode(9, "无效的版本");
    public static final ResultCode UNKNOWN_METHOD = new ResultCode(10, "未知的请求方法");
    public static final ResultCode UNKNOWN_RESULT_FORMAT = new ResultCode(11, "未知的结果格式");
    public static final ResultCode RPC_ERROR = new ResultCode(12, "服务器内部错误");
    public static final ResultCode LACK_APP_ID = new ResultCode(13, "Lack appId");
    public static final ResultCode LACK_SIG = new ResultCode(14, "Lack sig");
    public static final ResultCode LACK_URL_TIMEOUT = new ResultCode(15, "Lack req_time");
    public static final ResultCode URL_TIMEOUT = new ResultCode(16, "客户端时间不正确");
    public static final ResultCode PARAM_LACK = new ResultCode(17, "缺少必要参数");
    public static final ResultCode UNKNOWN_TYPE = new ResultCode(18, "未知类型");
    public static final ResultCode PARAM_VALUE_ERROR = new ResultCode(19, "参数值错误");
    public static final ResultCode LACK_UNIQID = new ResultCode(20, "Lack uniqid");
    public static final ResultCode LAWLESS_UNIQID = new ResultCode(21, "Lawless uniqid");
    public static final ResultCode LENGTH_TOO_LONG = new ResultCode(22, "参数过长");
    public static final ResultCode ERR_REFRESH = new ResultCode(23, "服务器繁忙，请稍后再试");
    public static final ResultCode IDENTITY_NOT_MATCH = new ResultCode(24, "请切换身份后再试");
    public static final ResultCode USER_NOT_COMPLETE = new ResultCode(25, "请先完善个人信息");
    public static final ResultCode FORCE_UPGRADE = new ResultCode(100, "请升级版本后再使用");
    public static final ResultCode CUSTOM_MESSAGE = new ResultCode(1049, "");
    public static final ResultCode OPERATE_FAILED = new ResultCode(1092, "操作失败");
    public static final ResultCode EC_ANTISPAM_CSRF_ATTACK = new ResultCode(5001, "无效请求，请刷新页面后重试");
    public static final ResultCode EC_DEVICE_SECURITY_VERIFY = new ResultCode(5011, "常用设备安全验证");
    public static final ResultCode RPC_EXCEPTION = new ResultCode(10001, "RPC Exception");
    public static final ResultCode BIZ_EXCEPTION = new ResultCode(10002, "BIZ Exception");
    public static final ResultCode RPC_TIMEOUT_EXCEPTION = new ResultCode(10003, "RPC TimeoutException");
    public static final ResultCode NOT_FOUND_EXCEPTION = new ResultCode(10004, "URL NOT FOUND");
    public static final ResultCode RPC_HYSTRIX_EXCEPTION = new ResultCode(10005, "异常限流");
    public static final int PC_NOT_LOGIN_REDIRECT = 1011;
    public static final int PC_IDENTITY_EXCEPTION = 602;
    public static final ResultCode EC_GROUP_ERROR_CUSTOM_SHOW = new ResultCode(7001, "群聊特殊错误码");
    public static final ResultCode WX_BASE_INFO_NOT_AUTH = new ResultCode(8001, "base info not auth");
    public static final ResultCode WX_USER_INFO_NOT_AUTH = new ResultCode(8002, "user info not auth");
    private int code;
    private String msg;

    public ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "ResultCode{code=" + this.code + ", msg='" + this.msg + '\'' + '}';
    }
}
