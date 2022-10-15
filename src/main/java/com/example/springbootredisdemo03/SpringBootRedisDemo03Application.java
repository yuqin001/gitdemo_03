package com.example.springbootredisdemo03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@MapperScan(basePackages = "com.example.springbootredisdemo03.dao")
public class SpringBootRedisDemo03Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisDemo03Application.class, args);
    }

}
