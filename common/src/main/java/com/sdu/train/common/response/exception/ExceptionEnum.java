package com.sdu.train.common.response.exception;

public enum ExceptionEnum {

    MEMBER_MOBILE_EXIST("手机号已注册"),
    MEMBER_MOBILE_NOT_EXIST("请先获取短信验证码"),
    VER_CODE_ERROR("短信验证码错误");
    private String msg;

    ExceptionEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ExceptionEnum{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
