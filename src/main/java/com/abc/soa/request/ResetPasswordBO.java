package com.abc.soa.request;


/**
 * Created by stuy on 2017/11/10.
 */
public class ResetPasswordBO {

    private String phone;

    private String password;

    private String token;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
