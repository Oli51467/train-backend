package com.sdu.train.member.controller;

import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.member.dto.MemberDTO;
import com.sdu.train.member.dto.MemberLoginDTO;
import com.sdu.train.member.resp.MemberVO;
import com.sdu.train.member.service.MemberService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping(value = "/hello")
    public ResponseResult test() {
        return ResponseResult.ok();
    }

    @PostMapping( "/register")
    @ResponseBody
    public ResponseResult register(@Valid @RequestBody  MemberDTO memberDTO) {
        return memberService.register(memberDTO);
    }

    @PostMapping("/verification/send")
    @ResponseBody
    public ResponseResult sendVerificationCode(@Valid @RequestBody MemberDTO memberDTO) {
        memberService.sendVerificationCode(memberDTO);
        return ResponseResult.ok();
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseResult login(@Valid @RequestBody MemberLoginDTO memberLoginDTO) {
        MemberVO memberVO = memberService.login(memberLoginDTO);
        return ResponseResult.ok(memberVO);
    }
}
