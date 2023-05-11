package com.sdu.train.common.response.exception;

public enum ExceptionEnum {

    MEMBER_MOBILE_EXIST("手机号已注册");
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
