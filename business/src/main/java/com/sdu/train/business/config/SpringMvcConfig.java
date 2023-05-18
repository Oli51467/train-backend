package com.sdu.train.business.config;

import com.sdu.train.common.interceptor.LogInterceptor;
import com.sdu.train.common.interceptor.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

   @Resource
   LogInterceptor logInterceptor;

   @Resource
   LoginInterceptor memberInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(logInterceptor)
               .addPathPatterns("/**");

       registry.addInterceptor(memberInterceptor)
              .addPathPatterns("/**")
              .excludePathPatterns(
                      "/business/api/hello"
              );
   }
}
