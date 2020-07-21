package com.sean;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableCaching
@MapperScan(basePackages = "com.sean.mapper")
@SpringBootApplication
public class SeanWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeanWebApplication.class, args);
    }
}
