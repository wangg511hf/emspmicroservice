package com.volvo.emspmicroservice.accountservice.controller;

import com.volvo.emspmicroservice.accountservice.domain.Account;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import com.volvo.emspmicroservice.accountservice.enumType.AccountStatus;
import com.volvo.emspmicroservice.common.domain.Result;
import com.volvo.emspmicroservice.accountservice.service.AccountService;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.common.query.PageQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final AccountService accountService;

    /**
     * Create Account API
     */
    @PostMapping("/create")
    public Result createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        if(accountService.isAccountExists(accountDTO.getEmail())) {
            throw new RuntimeException("Email already exists!");
        }

        Account account = new Account(accountDTO);
        accountService.save(account);

        return Result.of(200, "Account created successfully!");
    }

    /**
     * Change status of Account API
     */
    @PatchMapping("/changeAccountStatus/{id}")
    public Result changeAccountStatus(@PathVariable("id") Integer id, @RequestBody @Valid AccountDTO accountDTO) {
        Account account = accountService.getById(id);
        if(account == null) {
            throw new RuntimeException("Account not found with id: " + id);
        }
        //account.setAccountStatus(new AccountStatusConverter().convert(accountDTO.getAccoutStatus()));
        account.setAccountStatus(AccountStatus.valueOf(accountDTO.getAccountStatus()));
        accountService.updateById(account);

        return Result.of(200, "Account status changed successfully!");
    }

    /**
     * Sort Account data by last_updated field in desc order, and pagination API
     */
    @GetMapping("/queryAccountPage")
    public PageDTO<AccountDTO> queryAccountPage(@RequestBody PageQuery query) {
        return accountService.queryAccountPage(query);
    }

    /**
     * Get Account data by id
     */
    @GetMapping("/getAccount/{id}")
    public AccountDTO getAccountById(@PathVariable("id") Integer id) {
        Account account = accountService.getById(id);
        if(account == null) {
            throw new RuntimeException("Account not found with id: " + id);
        }

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setName(account.getName());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setAccountStatus(account.getAccountStatus().name());
        accountDTO.setCreateTime(account.getCreateTime());
        accountDTO.setLastUpdated(account.getLastUpdated());

        return accountDTO;
    }
}
