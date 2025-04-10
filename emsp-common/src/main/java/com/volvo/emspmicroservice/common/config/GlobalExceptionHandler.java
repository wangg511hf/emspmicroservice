package com.volvo.emspmicroservice.common.config;

import com.volvo.emspmicroservice.common.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("Exception occurred: ", e);
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(BindException e) {
        log.error("BindException occurred: ", e);
        return Result.fail(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
