package com.abc.soa.response;

/**
 * Created by stuy on 2018/2/9.
 */
public class CompanySoaBo {

    private String nsrsbh;//纳税人识别号

    private String nsrmc;//纳税人名称

    private String ywlx;//业务类型

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    public String getYwlx() {
        return ywlx;
    }

    public void setYwlx(String ywlx) {
        this.ywlx = ywlx;
    }
}
