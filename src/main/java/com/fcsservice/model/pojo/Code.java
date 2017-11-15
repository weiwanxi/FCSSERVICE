package com.fcsservice.model.pojo;

import java.io.Serializable;

public class Code implements Serializable {
    private String mail;

    private String code;

    private Integer typee;

    private String time;

    private static final long serialVersionUID = 1L;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return typee;
    }

    public void setType(Integer typee) {
        this.typee = typee;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}