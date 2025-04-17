package com.volvo.emspmicroservice.accountservice.infrastructure.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.volvo.emspmicroservice.accountservice.domain.enumType.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@TableName("emsp_account")
@NoArgsConstructor
@AllArgsConstructor
public class AccountDO {
    // Auto generated id, primary key
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    // Unique key
    @TableField(value = "email")
    private String email;

    @TableField(value = "name")
    private String name;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    // Account Status: CREATED, ACTIVATED, DEACTIVATED
    @TableField(value = "account_status")
    private AccountStatus accountStatus;

    // Generate create_time when create account
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
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
