package com.sdu.train.common.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sdu.train.common.util.JwtUtil;
import com.sdu.train.common.context.LoginMemberContext;
import com.sdu.train.common.viewObject.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器：Spring框架特有的，常用于登录校验，权限校验，请求日志打印
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取header的token参数
        logger.info("LoginInterceptor begin...");
        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token)) {
            logger.info("获取会员登录token：{}", token);
            JSONObject loginMember = JwtUtil.getJSONObject(token);
            logger.info("当前登录会员：{}", loginMember);
            // 取得当前登陆用户放入到ThreadLocal中
            MemberVO member = JSONUtil.toBean(loginMember, MemberVO.class);
            LoginMemberContext.setMember(member);
        }
        logger.info("LoginInterceptor end...");
        return true;
    }

}
