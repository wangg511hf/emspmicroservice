package com.volvo.emspmicroservice.accountservice.integration.controller;

import com.volvo.emspmicroservice.accountservice.dto.AccountDTO;
import com.volvo.emspmicroservice.accountservice.controller.AccountController;
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
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail("Emma123@gmail.com");
        accountDTO.setName("Emma");
        accountDTO.setUsername("emma666666");
        accountDTO.setPassword("Emma123456!");
        accountDTO.setAccountStatus("CREATED");

        accountController.createAccount(accountDTO);
    }

    /**
     * update account status API test case
     */
    @Test
    public void testChangeAccountStatus() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(2);
        accountDTO.setEmail("Ll1111@126.com");
        accountDTO.setName("Lily");
        accountDTO.setUsername("Lily12345");
        accountDTO.setPassword("Ll12345678");
        accountDTO.setAccountStatus("DEACTIVATED");

        Result res = accountController.changeAccountStatus(accountDTO.getId(), accountDTO);
        assertEquals(Result.of(200, "Account status changed successfully!"), res);
    }
}