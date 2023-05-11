package com.sdu.train.common.response.exception;

import com.sdu.train.common.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 所有系统异常统一处理，出现则说明有bug
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult exceptionHandler(Exception e) {
        logger.error("系统异常：{}", e.getMessage());
        return ResponseResult.fail("系统异常" + e.getMessage());
    }

    /**
     * 业务异常统一处理
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseResult exceptionHandler(BusinessException e) {
        logger.error("业务异常：{}", e.getE().getMsg());
        return ResponseResult.fail(e.getE().getMsg());
    }

}
