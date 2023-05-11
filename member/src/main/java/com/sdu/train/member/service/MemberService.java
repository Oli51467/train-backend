package com.sdu.train.member.service;

import com.sdu.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("MemberService")
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public Long count() {
        return memberMapper.countByExample(null);
    }
}
