package com.sdu.train.common.response.exception;

public class BusinessException extends RuntimeException {

    private ExceptionEnum e;

    public BusinessException(ExceptionEnum e) {
        this.e = e;
    }

    public ExceptionEnum getE() {
        return e;
    }

    public void setE(ExceptionEnum e) {
        this.e = e;
    }
}
