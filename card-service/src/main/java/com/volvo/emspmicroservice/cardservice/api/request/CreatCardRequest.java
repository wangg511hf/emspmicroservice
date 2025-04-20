package com.volvo.emspmicroservice.cardservice.api.request;

import jakarta.validation.constraints.*;
import java.util.Date;

public class CreatCardRequest {

    private Integer id;

    @NotBlank(message = "Card number can't be blank!")
    private String cardNum;

    private Integer accountId;

    private String contractId;

    @Pattern(regexp = "CREATED|ASSIGNED|ACTIVATED|DEACTIVATED",
            message = "Card status must be CREATED, ASSIGNED, ACTIVATED or DEACTIVATED")
    private String cardStatus;

    private Date createTime;

    private Date lastUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
