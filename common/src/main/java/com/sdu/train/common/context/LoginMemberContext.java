package com.sdu.train.common.context;

import com.sdu.train.common.viewObject.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginMemberContext {

    private static final Logger logger = LoggerFactory.getLogger(LoginMemberContext.class);

    private static ThreadLocal<MemberVO> member = new ThreadLocal<>();

    public static MemberVO getMember() {
        return member.get();
    }

    public static void setMember(MemberVO member) {
        LoginMemberContext.member.set(member);
    }

    public static Long getId() {
        try {
            return member.get().getId();
        } catch (Exception e) {
            logger.error("获取登录会员信息异常", e);
            throw e;
        }
    }

}
