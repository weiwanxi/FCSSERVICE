package com.fcsservice.model;

/**
 * Created by YE on 2017/10/18.
 */
public class Account {
    private String user_account;
    private String user_password;
    private int user_type;
    private String user_regtime;
    private int user_status;

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUser_regtime() {
        return user_regtime;
    }

    public void setUser_regtime(String user_regtime) {
        this.user_regtime = user_regtime;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }
}
