package com.abc.soa.request;

/**
 * Created by stuy on 2017/7/24.
 */
public class UserPasswordBo {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getXphone() {
        return xphone;
    }

    public void setXphone(String xphone) {
        this.xphone = xphone;
    }

    private String xphone;

    private String type;

    private String querenphone;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuerenphone() {
        return querenphone;
    }

    public void setQuerenphone(String querenphone) {
        this.querenphone = querenphone;
    }
}
