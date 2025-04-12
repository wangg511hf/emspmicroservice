package com.volvo.emspmicroservice.common.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfiguration {

    @Bean
    public Logger.Level loglevel() {
        return Logger.Level.BASIC;
    }
}
