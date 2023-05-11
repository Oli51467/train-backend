package com.sdu.train.member.controller;

import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.member.dto.MemberDTO;
import com.sdu.train.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public Long count() {
        return memberService.count();
    }

    @PostMapping("/register")
    public ResponseResult register(MemberDTO memberDTO) {
        return memberService.register(memberDTO);
    }
}
