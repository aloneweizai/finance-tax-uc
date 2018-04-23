package com.abc.common.soa.response;

/**
 * Created by relic5 on 6/12/16.
 */
public class AuthAccessTokenResponse extends BaseResponse {

    private String token;

    private String expires_in;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
