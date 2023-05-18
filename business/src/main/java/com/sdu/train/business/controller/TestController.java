package com.sdu.train.business.controller;

import com.sdu.train.common.response.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/hello")
    public ResponseResult test() {
        return ResponseResult.ok();
    }
}
