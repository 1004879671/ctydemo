package com.ccty.service.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.ccty.service.common.dao"})
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.ccty.service.common.CommonApplication.class, args);
    }

}
