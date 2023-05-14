package com.sdu.train.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {

    /**
     * 成功返回的状态码
     */
    SUCCESS(200, "success", Boolean.TRUE),

    FAIL(400, "fail", Boolean.FALSE),
    ;

    private int code;
    private String msg;
    private Boolean success;
}
