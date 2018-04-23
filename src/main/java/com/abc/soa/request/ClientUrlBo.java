package com.abc.soa.request;

/**
 * Created by stuy on 2017/8/11.
 */
public class ClientUrlBo {

    /**
     * 跳转地址
     */
    private String url;

    /**
     * user-token
     */
    private String token;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOnly() {
        return only;
    }

    public void setOnly(String only) {
        this.only = only;
    }

    /**

     * 终端标识
     */
    private String only;

    /**
     * 客户端accessToken
     */
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
