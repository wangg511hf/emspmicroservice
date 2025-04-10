package com.volvo.emspmicroservice.common.client;

import com.volvo.emspmicroservice.common.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "account-service")
public interface AccountClient {

    @GetMapping("/account/client")
    AccountDTO getAccountById(@RequestParam int id);
}
