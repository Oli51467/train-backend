package com.sdu.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.exception.BusinessException;
import com.sdu.train.common.response.exception.ExceptionEnum;
import com.sdu.train.member.domain.Member;
import com.sdu.train.member.domain.MemberExample;
import com.sdu.train.member.dto.MemberDTO;
import com.sdu.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sdu.train.common.util.SnowUtil.getSnowflakeNextId;

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
        // 自增id不适合分布式数据库，uuid会影响索引效率，因为uuid是无序的，用无序id来构建有序的索引目录性能上存在问题 这里使用雪花算法
        member.setId(getSnowflakeNextId());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return ResponseResult.ok(member.getId());
    }
}
