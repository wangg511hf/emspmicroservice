package com.volvo.emspmicroservice.accountservice.unit.controller;

import com.volvo.emspmicroservice.common.dto.AccountDTO;
import com.volvo.emspmicroservice.accountservice.controller.AccountController;
import com.volvo.emspmicroservice.accountservice.domain.Account;
import com.volvo.emspmicroservice.common.domain.Result;
import com.volvo.emspmicroservice.accountservice.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Test
    public void createAccount_SuccessWhenInputValid() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1);
        accountDTO.setEmail("test@test.com");
        accountDTO.setName("testAccount");
        accountDTO.setUsername("testUsername");
        accountDTO.setPassword("testPassword");
        accountDTO.setAccountStatus("CREATED");

        when(accountService.isAccountExists(accountDTO.getEmail())).thenReturn(false);
        when(accountService.save(any(Account.class))).thenReturn(true);

        Result testRes = accountController.createAccount(accountDTO);
        assertEquals(Result.of(200, "Account created successfully!"), testRes);

        verify(accountService).isAccountExists(accountDTO.getEmail());
        verify(accountService).save(any(Account.class));
    }

    @Test
    public void createAccount_FailWhenEmailExists() {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(1);
        accountDTO.setName("testAccount");
        accountDTO.setEmail("test@test.com");
        accountDTO.setUsername("testUsername");
        accountDTO.setPassword("testPassword");
        accountDTO.setAccountStatus("CREATED");

        when(accountService.isAccountExists(accountDTO.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> accountController.createAccount(accountDTO)
        );

        assertEquals("Email already exists!", exception.getMessage());
        verify(accountService, never()).save(any());
    }
}
