package com.abc.soa.request;

/**
 * Created by stuy on 2017/9/5.
 */
public class CallbackPayBo implements java.io.Serializable{

    private String status;

    private Boolean bool;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }
}
