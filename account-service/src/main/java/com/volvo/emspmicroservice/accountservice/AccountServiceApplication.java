package com.volvo.emspmicroservice.accountservice;

import com.volvo.emspmicroservice.common.client.AccountClient;
import com.volvo.emspmicroservice.common.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.volvo.emspmicroservice.accountservice.mapper")
@ComponentScan("com.volvo.emspmicroservice")
@EnableFeignClients(clients = AccountClient.class, defaultConfiguration = DefaultFeignConfiguration.class)
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }
}
