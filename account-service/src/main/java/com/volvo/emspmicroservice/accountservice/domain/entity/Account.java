package com.volvo.emspmicroservice.accountservice.domain.entity;

import com.volvo.emspmicroservice.accountservice.domain.enumType.AccountStatus;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class Account {

    private Integer id;
    private String email;
    private String name;
    private String username;
    private String password;
    private AccountStatus accountStatus;
    private Date createTime;
    private Date lastUpdated;

    public void activate() {
        this.accountStatus = AccountStatus.ACTIVATED;
    }

    public void deactivate() {
        this.accountStatus = AccountStatus.DEACTIVATED;
    }

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
