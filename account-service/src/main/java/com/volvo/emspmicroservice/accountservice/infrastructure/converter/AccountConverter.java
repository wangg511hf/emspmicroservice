package com.volvo.emspmicroservice.accountservice.infrastructure.converter;

import com.volvo.emspmicroservice.accountservice.domain.entity.Account;
import com.volvo.emspmicroservice.accountservice.infrastructure.DO.AccountDO;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public Account toDomain(AccountDO accountDO) {
        return new Account(
                accountDO.getId(),
                accountDO.getName(),
                accountDO.getEmail(),
                accountDO.getUsername(),
                accountDO.getPassword(),
                accountDO.getAccountStatus(),
                accountDO.getCreateTime(),
                accountDO.getLastUpdated()
        );
    }

    public AccountDO fromDomain(Account account) {
        AccountDO accountDO = new AccountDO();
        accountDO.setId(account.getId());
        accountDO.setName(account.getName());
        accountDO.setEmail(account.getEmail());
        accountDO.setUsername(account.getUsername());
        accountDO.setPassword(account.getPassword());
        accountDO.setAccountStatus(account.getAccountStatus());
        accountDO.setCreateTime(account.getCreateTime());
        accountDO.setLastUpdated(account.getLastUpdated());

        return accountDO;
    }
}
