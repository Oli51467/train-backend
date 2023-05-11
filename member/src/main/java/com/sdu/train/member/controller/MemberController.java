package com.sdu.train.member.controller;

import com.sdu.train.common.response.BaseResponse;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.member.dto.MemberDTO;
import com.sdu.train.member.dto.MemberLoginDTO;
import com.sdu.train.member.entity.MemberVO;
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

    @PostMapping("/verification/send")
    @ResponseBody
    public ResponseResult sendVerificationCode(@Valid MemberDTO memberDTO) {
        memberService.sendVerificationCode(memberDTO);
        return ResponseResult.ok();
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(@Valid MemberLoginDTO memberLoginDTO) {
        MemberVO memberVO = memberService.login(memberLoginDTO);
        return ResponseResult.ok(memberVO);
    }
}
