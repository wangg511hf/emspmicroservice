package com.volvo.emspmicroservice.accountservice.integration.controller;

import com.volvo.emspmicroservice.accountservice.api.request.ChangeAccountStatusRequest;
import com.volvo.emspmicroservice.accountservice.api.request.CreateAccountRequest;
import com.volvo.emspmicroservice.accountservice.api.response.ApiResponse;
import com.volvo.emspmicroservice.accountservice.domain.entity.Account;
import com.volvo.emspmicroservice.common.dto.AccountDTO;
import com.volvo.emspmicroservice.accountservice.api.controller.AccountController;
import com.volvo.emspmicroservice.common.domain.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AccountControllerIT {

    @Autowired
    private AccountController accountController;

    /**
     * create account API test case
     */
    @Test
    public void testCreateAccount() {
        CreateAccountRequest car = new CreateAccountRequest();
        car.setEmail("Emma123@gmail.com");
        car.setName("Emma");
        car.setUsername("emma666666");
        car.setPassword("Emma123456!");
        car.setAccountStatus("CREATED");

        accountController.createAccount(car);
    }

    /**
     * update account status API test case
     */
    @Test
    public void testChangeAccountStatus() {
        String email = "Emma123@gmail.com";
        ChangeAccountStatusRequest casr = new ChangeAccountStatusRequest();
        casr.setAccountStatus("DEACTIVATED");

        ApiResponse<Account> response = accountController.changeAccountStatus(email, casr);
        assertEquals(200, response.getCode());
    }
}