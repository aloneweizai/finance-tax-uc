package com.abc.soa.request.userinfo;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-24
 * @Time:10:42
 */
public class UserDzsbReset {
    private String nsrsbh;//纳税人识别号或社会信用代码
    private String frmc;//纳税人代表名称
    private String frzjh;//法人代表证件号
    private String phone;
    private String code;

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getFrmc() {
        return frmc;
    }

    public void setFrmc(String frmc) {
        this.frmc = frmc;
    }

    public String getFrzjh() {
        return frzjh;
    }

    public void setFrzjh(String frzjh) {
        this.frzjh = frzjh;
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
}
