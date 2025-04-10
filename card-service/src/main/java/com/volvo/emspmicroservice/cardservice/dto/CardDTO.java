package com.volvo.emspmicroservice.cardservice.dto;

import com.volvo.emspmicroservice.cardservice.domain.Card;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CardDTO {

    private int id;

    @NotBlank(message = "Card number can't be blank!")
    private String cardNum;

    private int accountId;

    private String contractId;

    @Pattern(regexp = "CREATED|ASSIGNED|ACTIVATED|DEACTIVATED",
            message = "Account status must be CREATED, ASSIGNED, ACTIVATED or DEACTIVATED")
    private String cardStatus;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardNum = card.getCardNum();
        this.accountId = card.getAccountId();
        this.contractId = card.getContractId();
        //this.cardStatus = card.getCardStatus().toString();
        this.cardStatus = card.getCardStatus().name();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }
}
