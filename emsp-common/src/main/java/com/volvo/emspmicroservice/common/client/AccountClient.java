package com.volvo.emspmicroservice.common.client;

import com.volvo.emspmicroservice.common.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "emsp-account-service")
public interface AccountClient {

    @GetMapping("/account/getAccount/{id}")
    AccountDTO getAccountById(@PathVariable("id") int id);
}