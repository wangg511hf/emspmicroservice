package com.volvo.emspmicroservice.accountservice.domain.converter;

import com.volvo.emspmicroservice.accountservice.api.request.CreateAccountRequest;
import com.volvo.emspmicroservice.accountservice.domain.entity.Account;
import com.volvo.emspmicroservice.accountservice.domain.enumType.AccountStatus;
import org.springframework.stereotype.Component;

@Component
public class AccountRequestConverter {

    public Account fromRequest(CreateAccountRequest createAccountRequest) {
        return new Account(
                createAccountRequest.getId(),
                createAccountRequest.getEmail(),
                createAccountRequest.getName(),
                createAccountRequest.getUsername(),
                createAccountRequest.getPassword(),
                AccountStatus.valueOf(createAccountRequest.getAccountStatus()),
                createAccountRequest.getCreateTime(),
                createAccountRequest.getLastUpdated()
        );
    }
}