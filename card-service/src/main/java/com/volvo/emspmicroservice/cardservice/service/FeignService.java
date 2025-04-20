package com.volvo.emspmicroservice.cardservice.service;

import com.volvo.emspmicroservice.cardservice.domain.entity.Account;
import com.volvo.emspmicroservice.cardservice.service.client.AccountClient;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeignService {

    private final AccountClient accountClient;

    public AccountDTO getAccountById(Integer accountId) {
        AccountDTO accountDTO = accountClient.getAccountById(accountId);

        return accountDTO;
    }
}
