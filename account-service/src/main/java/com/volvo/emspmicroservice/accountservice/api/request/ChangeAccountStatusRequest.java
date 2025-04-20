package com.volvo.emspmicroservice.accountservice.api.request;

import jakarta.validation.constraints.*;

public class ChangeAccountStatusRequest {

    @Pattern(regexp = "CREATED|ACTIVATED|DEACTIVATED",
            message = "Account status must be CREATED, ACTIVATED or DEACTIVATED")
    private String accountStatus;

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
