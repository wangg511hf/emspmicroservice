package com.volvo.emspmicroservice.cardservice.infrastructure.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.volvo.emspmicroservice.cardservice.domain.enumType.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@TableName("emsp_card")
@NoArgsConstructor
@AllArgsConstructor
public class CardDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "card_num")
    private String cardNum;

    // Foreign key
    @TableField(value = "account_id")
    private Integer accountId;

    // Auto generated EMAID format field
    @TableField(value = "contract_id")
    private String contractId;

    // Card status: CREATED, ASSIGNED, ACTIVATED, DEACTIVATED
    @TableField(value = "card_status")
    private CardStatus cardStatus;

    // Generate create_time when create Card
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    // Update last_updated when update account
    @TableField(value = "last_updated", fill = FieldFill.INSERT_UPDATE)
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
