package com.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = "com.seckill")
@MapperScan("com.seckill.dao")//扫描dao类
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
