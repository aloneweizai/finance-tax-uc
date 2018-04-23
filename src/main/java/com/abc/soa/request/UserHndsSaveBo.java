package com.abc.soa.request;

/**
 * Created by stuy on 2017/7/26.
 */
public class UserHndsSaveBo {
    private String username;
    private String subuser;
    private String password;

    private String xphone;
    private String code;


    private String nsrmc;

    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public String getXphone() {
        return xphone;
    }

    public void setXphone(String xphone) {
        this.xphone = xphone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubuser() {
        return subuser;
    }

    public void setSubuser(String subuser) {
        this.subuser = subuser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
