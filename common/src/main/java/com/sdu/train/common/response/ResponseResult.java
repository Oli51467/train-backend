package com.sdu.train.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一的公共响应体
 */
@Data
@AllArgsConstructor
public class ResponseResult implements Serializable {

    // 返回状态码
    private Integer code;

    // 返回信息
    private String msg;

    // 是否成功
    private Boolean success;

    // 数据
    private Object data;

    public static ResponseResult ok() {
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), ResponseCode.SUCCESS.getSuccess(), null);
    }

    public static ResponseResult ok(Object o) {
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), ResponseCode.SUCCESS.getSuccess(), o);
    }

    public static ResponseResult fail(String errorMessage) {
        return new ResponseResult(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg(), ResponseCode.FAIL.getSuccess(), errorMessage);
    }
}
