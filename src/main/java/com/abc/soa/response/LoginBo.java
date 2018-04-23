package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

import java.io.Serializable;

/**
 * Created by stuy on 2017/7/19.
 */
public class LoginBo  extends BaseResponse{

    private String expires_in;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public UserBo getUser() {
        return user;
    }

    public void setUser(UserBo user) {
        this.user = user;
    }

    private UserBo user;
}
