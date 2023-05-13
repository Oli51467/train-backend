package com.sdu.train.gateway.filter;

import com.sdu.train.gateway.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoginFilter implements GlobalFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // 排除不需要过滤的请求
        if (path.contains("/admin") || path.contains("/hello") || path.contains("/member/api/login") || path.contains("/member/api/verification/send")) {
            logger.info("No Need Auth");
            return chain.filter(exchange);
        } else {
            logger.info("JwtAuth begin");
        }
        // 获取header的token参数
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (null == token || token.isEmpty()) {
            logger.info("token为空，请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        // header有token，验证token是否有效是否过期
        boolean validate = JwtUtil.validate(token);
        if (validate) {
            logger.info("token有效，请求放行");
            return chain.filter(exchange);
        } else {
            logger.warn("token无效，请求拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
