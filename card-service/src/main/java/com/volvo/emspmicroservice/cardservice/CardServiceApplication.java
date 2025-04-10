package com.volvo.emspmicroservice.cardservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan("com.volvo.emspmicroservice.cardservice.mapper")
@ComponentScan("com.volvo.emspmicroservice")
@EnableFeignClients(basePackages = "com.volvo.emspmicroservice.common.client")
public class CardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class, args);
    }
}
