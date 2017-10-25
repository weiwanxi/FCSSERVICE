package com.fcsservice.model.pojo;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private String userId;

    private String userAccount;

    private String userPassword;

    private Integer userType;

    private String userRegtime;

    private Integer userStatus;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserRegtime() {
        return userRegtime;
    }

    public void setUserRegtime(String userRegtime) {
        this.userRegtime = userRegtime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}