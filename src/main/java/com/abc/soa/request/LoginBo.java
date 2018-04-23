package com.abc.soa.request;

/**
 * Created by stuy on 2017/11/11.
 */
public class LoginBo {

    private String username;

    private String password;

    private String type;

    private String phone;

    private String code;

    private String recallurl;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRecallurl() {
        return recallurl;
    }

    public void setRecallurl(String recallurl) {
        this.recallurl = recallurl;
    }
}
