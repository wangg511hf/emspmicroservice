package com.volvo.emspmicroservice.accountservice.api.controller;

import com.volvo.emspmicroservice.accountservice.api.request.ChangeAccountStatusRequest;
import com.volvo.emspmicroservice.accountservice.api.request.CreateAccountRequest;
import com.volvo.emspmicroservice.accountservice.api.response.ApiResponse;
import com.volvo.emspmicroservice.accountservice.domain.entity.Account;
import com.volvo.emspmicroservice.accountservice.service.AccountService;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import com.volvo.emspmicroservice.common.dto.PageDTO;
import com.volvo.emspmicroservice.common.query.PageQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Account> createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest) {
        Account account = accountService.createAccount(createAccountRequest);

        return ApiResponse.success(account);
    }

    /**
     * Change status of Account API
     */
    @PatchMapping("{email}/status")
    public ApiResponse<Account> changeAccountStatus(@PathVariable("email") String email,
                                      @RequestBody @Valid ChangeAccountStatusRequest changeAccountStatusRequest) {
        Account account = accountService.changeAccountStatus(email, changeAccountStatusRequest);

        return ApiResponse.success(account);
    }

    /**
     * Sort Account data by last_updated field in desc order, and pagination API
     */
    @GetMapping("/search")
    public PageDTO<Account> queryAccountPage(@RequestBody PageQuery query) {
        return accountService.queryAccountPage(query);
    }

    /**
     * Get Account data by id
     */
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable("id") Integer id) {
        Account account = accountService.getAccountById(id);

        return account;
    }
}
