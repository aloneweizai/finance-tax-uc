package com.abc.soa.request;

/**
 * Created by stuy on 2017/7/18.
 */
public class RegisterReq {
    private boolean status;
    private String verifyingCode;
    private String password;

    private String phone;

    private String salt;

    private String province;
    private String city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getVerifyingType() {
        return verifyingType;
    }

    public void setVerifyingType(String verifyingType) {
        this.verifyingType = verifyingType;
    }

    private String verifyingType;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getVerifyingCode() {
        return verifyingCode;
    }

    public void setVerifyingCode(String verifyingCode) {
        this.verifyingCode = verifyingCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
