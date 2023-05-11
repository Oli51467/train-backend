package com.sdu.train.member.controller;

import com.sdu.train.common.response.BaseResponse;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.member.dto.MemberDTO;
import com.sdu.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@BaseResponse
public class MemberController {

    @Resource
    private MemberService memberService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseResult register(@Valid MemberDTO memberDTO) {
        return memberService.register(memberDTO);
    }
}
