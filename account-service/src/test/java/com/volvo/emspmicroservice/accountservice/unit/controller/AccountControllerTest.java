package com.volvo.emspmicroservice.accountservice.unit.controller;

import com.volvo.emspmicroservice.accountservice.api.request.CreateAccountRequest;
import com.volvo.emspmicroservice.accountservice.api.response.ApiResponse;
import com.volvo.emspmicroservice.accountservice.domain.entity.Account;
import com.volvo.emspmicroservice.accountservice.infrastructure.DO.AccountDO;
import com.volvo.emspmicroservice.accountservice.infrastructure.repository.AccountRepository;
import com.volvo.emspmicroservice.accountservice.api.controller.AccountController;
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

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void createAccount_SuccessWhenInputValid() {
        CreateAccountRequest car = new CreateAccountRequest();
        car.setId(1);
        car.setEmail("test@test.com");
        car.setName("testAccount");
        car.setUsername("testUsername");
        car.setPassword("testPassword");
        car.setAccountStatus("CREATED");

        when(accountRepository.isAccountExists(car.getEmail())).thenReturn(false);
        when(accountRepository.save(any(AccountDO.class))).thenReturn(true);

        ApiResponse<Account> testRes = accountController.createAccount(car);
        assertEquals(200, testRes.getCode());

        verify(accountRepository).isAccountExists(car.getEmail());
        verify(accountRepository).save(any(AccountDO.class));
    }

    @Test
    public void createAccount_FailWhenEmailExists() {
        CreateAccountRequest car = new CreateAccountRequest();
        car.setId(1);
        car.setName("testAccount");
        car.setEmail("test@test.com");
        car.setUsername("testUsername");
        car.setPassword("testPassword");
        car.setAccountStatus("CREATED");

        when(accountRepository.isAccountExists(car.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> accountController.createAccount(car)
        );

        assertEquals("Email already exists!", exception.getMessage());
        verify(accountRepository, never()).save(any());
    }
}
