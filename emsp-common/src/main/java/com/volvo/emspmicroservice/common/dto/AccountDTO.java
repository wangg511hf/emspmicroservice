package com.volvo.emspmicroservice.common.dto;

import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@NoArgsConstructor
public class AccountDTO {

    private Integer id;
    @NotBlank(message = "Email address can't be blank!")
    @Email(message = "Email address format is not correct!")
    private String email;

    @NotBlank(message = "Name can't be blank!")
    @Size(min = 3, max = 20, message = "The length of name must be between 3 and 20!")
    private String name;

    @NotBlank(message = "Username can not be blank!")
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
            message = "Password must cantain capital letter, small letter and number, and the length should be more than 8")
    private String password;

    @Pattern(regexp = "CREATED|ACTIVATED|DEACTIVATED",
            message = "Account status must be CREATED, ACTIVATED or DEACTIVATED")
    private String accountStatus;

    private Date createTime;

    private Date lastUpdated;

    /*public AccountDTO(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.name = account.getName();
        this.username = account.getUsername();
        this.password = account.getPassword();
        //this.accoutStatus = account.getAccountStatus().toString();
        this.accountStatus = account.getAccountStatus().name();
        this.createTime = account.getCreateTime();
        this.lastUpdated = account.getLastUpdated();
    }*/

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

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
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
