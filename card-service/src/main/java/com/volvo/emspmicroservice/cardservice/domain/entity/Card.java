package com.volvo.emspmicroservice.cardservice.domain.entity;

import com.volvo.emspmicroservice.cardservice.domain.enumType.CardStatus;
import lombok.AllArgsConstructor;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
public class Card {

    private Integer id;
    private String cardNum;
    private Integer accountId;
    private String contractId;
    private CardStatus cardStatus;
    private Date createTime;
    private Date lastUpdated;

    public void create() {
        cardStatus = CardStatus.CREATED;
    }

    public void assign() {
        cardStatus = CardStatus.ASSIGNED;
    }

    public void activate() {
        cardStatus = CardStatus.ACTIVATED;
    }

    public void deactivate() {
        cardStatus = CardStatus.DEACTIVATED;
    }

    public void generateEMAID(String countryCode, String vendorCode) {
        Long randomNum = ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);
        this.setContractId(String.format("%s-%s-%d", countryCode, vendorCode, randomNum));
    }

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

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
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