package com.volvo.emspmicroservice.cardservice;

import com.volvo.emspmicroservice.cardservice.service.client.AccountClient;
import com.volvo.emspmicroservice.common.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.volvo.emspmicroservice.cardservice.infrastructure.mapper")
@ComponentScan("com.volvo.emspmicroservice")
@EnableFeignClients(clients = AccountClient.class, defaultConfiguration = DefaultFeignConfiguration.class)
public class CardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class, args);
    }
}