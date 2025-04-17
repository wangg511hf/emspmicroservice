package com.volvo.emspmicroservice.accountservice.service;

import com.volvo.emspmicroservice.accountservice.api.request.ChangeAccountStatusRequest;
import com.volvo.emspmicroservice.accountservice.api.request.CreateAccountRequest;
import com.volvo.emspmicroservice.accountservice.domain.converter.AccountRequestConverter;
import com.volvo.emspmicroservice.accountservice.domain.entity.Account;
import com.volvo.emspmicroservice.accountservice.domain.enumType.AccountStatus;
import com.volvo.emspmicroservice.accountservice.infrastructure.repository.AccountRepository;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.common.query.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountRequestConverter accountRequestConverter;

    public Account createAccount(CreateAccountRequest createAccountRequest) {
        Account account = accountRequestConverter.fromRequest(createAccountRequest);

        if(account.getId() == null && !accountRepository.isAccountExists(account.getEmail())) {
            Account res = accountRepository.createAccount(account);
            return res;
        }

        throw new RuntimeException("Account created failed!");
    }

    public Account changeAccountStatus(String email, ChangeAccountStatusRequest changeAccountStatusRequest) {
        if(!accountRepository.isAccountExists(email)) {
            throw new RuntimeException("Account does not exist!");
        }

        Account account = accountRepository.getByEmail(email);
        if(changeAccountStatusRequest.getAccountStatus().equals("CREATED")) {
            return account;
        } else if(changeAccountStatusRequest.getAccountStatus().equals("ACTIVATED")) {
            account.activate();
        } else if(changeAccountStatusRequest.getAccountStatus().equals("DEACTIVATED")) {
            account.deactivate();
        } else {
            throw new RuntimeException("Wrong Account status input!");
        }
        Account res = accountRepository.changeAccountStatus(account);

        return res;
    }

    public PageDTO<Account> queryAccountPage(PageQuery query) {
        if(query.getPageNum() == null || query.getPageSize() == null) {
            throw new RuntimeException("Page number or page size is missing!");
        }

        PageDTO<Account> res = accountRepository.queryAccountPage(query);
        return res;
    }

    public Account getAccountById(Integer id) {
        Account account = accountRepository.getAccountById(id);
        if(account == null) {
            throw new RuntimeException("Account does not exist!");
        }

        return account;
    }
}
