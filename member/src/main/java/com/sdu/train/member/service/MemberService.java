package com.sdu.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.exception.BusinessException;
import com.sdu.train.common.response.exception.ExceptionEnum;
import com.sdu.train.member.domain.Member;
import com.sdu.train.member.domain.MemberExample;
import com.sdu.train.member.mapper.MemberMapper;
import com.sdu.train.member.dto.MemberDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MemberService")
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public ResponseResult register(MemberDTO memberDTO) {
        String mobile = memberDTO.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        if (CollUtil.isNotEmpty(list)) {
            throw new BusinessException(ExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return ResponseResult.ok(member.getId());
    }
}
