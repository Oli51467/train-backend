package com.sdu.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.sdu.train.common.response.ResponseResult;
import com.sdu.train.common.response.exception.BusinessException;
import com.sdu.train.common.response.exception.ExceptionEnum;
import com.sdu.train.common.util.JwtUtil;
import com.sdu.train.member.domain.Member;
import com.sdu.train.member.domain.MemberExample;
import com.sdu.train.member.dto.MemberDTO;
import com.sdu.train.member.dto.MemberLoginDTO;
import com.sdu.train.member.mapper.MemberMapper;
import com.sdu.train.member.viewObject.MemberVO;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sdu.train.common.util.SnowUtil.getSnowflakeNextId;

@Service("MemberService")
public class MemberService {

    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Resource
    private MemberMapper memberMapper;

    public ResponseResult register(MemberDTO memberDTO) {
        String mobile = memberDTO.getMobile();
        Member memberDB = selectByMobile(mobile);

        if (ObjectUtil.isNotNull(memberDB)) {
            throw new BusinessException(ExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        // 自增id不适合分布式数据库，uuid会影响索引效率，因为uuid是无序的，用无序id来构建有序的索引目录性能上存在问题 这里使用雪花算法
        member.setId(getSnowflakeNextId());
        member.setMobile(mobile);

        memberMapper.insert(member);
        return ResponseResult.ok(member.getId());
    }

    public MemberVO login(MemberLoginDTO memberLoginDTO) {
        String mobile = memberLoginDTO.getMobile();
        String code = memberLoginDTO.getCode();
        Member memberDB = selectByMobile(mobile);

        if (ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(ExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }
        if (!"8888".equals(code)) {
            throw new BusinessException(ExceptionEnum.VER_CODE_ERROR);
        }
        MemberVO memberVO =  BeanUtil.copyProperties(memberDB, MemberVO.class);
        String token = JwtUtil.createToken(memberVO.getId(), memberVO.getMobile());
        memberVO.setToken(token);
        return memberVO;
    }

    public void sendVerificationCode(MemberDTO memberDTO) {
        String mobile = memberDTO.getMobile();
        Member memberDB = selectByMobile(mobile);

        // 如果手机号不存在，则插入一条记录
        if (ObjectUtil.isNull(memberDB)) {
            logger.info("注册了一条手机记录, {}", mobile);
            Member member = new Member();
            member.setId(getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }

        // 生成验证码
        String code = RandomUtil.randomString(4);
        logger.info("生成短信验证码: {}", code);
        // 保存短信记录表
    }

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
