package com.volvo.emspmicroservice.cardservice.api.request;

import jakarta.validation.constraints.*;

public class AssignCardRequest {

    @NotBlank(message = "Card id can't be blank!")
    private Integer id;

    @NotBlank(message = "AccountId can't be blank!")
    private Integer accountId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
