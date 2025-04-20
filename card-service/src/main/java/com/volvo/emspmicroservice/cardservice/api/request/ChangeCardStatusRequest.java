package com.volvo.emspmicroservice.cardservice.api.request;

import jakarta.validation.constraints.*;

public class ChangeCardStatusRequest {

    @NotBlank(message = "Card id can't be blank!")
    private Integer id;

    @Pattern(regexp = "CREATED|ASSIGNED|ACTIVATED|DEACTIVATED",
            message = "Card status must be CREATED, ASSIGNED, ACTIVATED or DEACTIVATED")
    private String cardStatus;

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
