package com.volvo.emspmicroservice.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.volvo.emspmicroservice")
public class EmspCommonApplicaiton {

    public static void main(String[] args) {
        SpringApplication.run(EmspCommonApplicaiton.class, args);
    }
}
