package com.sdu.train.business.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan("com.sdu")
@MapperScan("com.sdu.train.business.mapper")
@EnableCaching
public class BusinessApplication {

    private static final Logger logger = LoggerFactory.getLogger(BusinessApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment env = app.run(args).getEnvironment();
        logger.info("启动成功！！");
        logger.info("测试地址: \thttp://127.0.0.1:{}{}/api/hello", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
    }

}
