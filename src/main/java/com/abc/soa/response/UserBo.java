package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by stuy on 2017/7/18.
 */
public class UserBo extends BaseResponse{


    private String id;
    private String username;
    private String phone;
    private String regMail;
    private String regIP;
    private String nickname;
    private String userPicturePath;
    private String maxUserPicturePath;
    private String midUserPicturePath;
    private String minUserPicturePath;
    private BigInteger points;

    private String wxopenid;
    private String wxheadimg;
    private String wxnickname;

    //可用礼包金额
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    public String getWxheadimg() {
        return wxheadimg;
    }

    public void setWxheadimg(String wxheadimg) {
        this.wxheadimg = wxheadimg;
    }

    public String getWxnickname() {
        return wxnickname;
    }

    public void setWxnickname(String wxnickname) {
        this.wxnickname = wxnickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private BigInteger exp;
    private String salt;

    public int getUsernameModifiedTimes() {
        return usernameModifiedTimes;
    }

    public void setUsernameModifiedTimes(int usernameModifiedTimes) {
        this.usernameModifiedTimes = usernameModifiedTimes;
    }

    private int usernameModifiedTimes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegMail() {
        return regMail;
    }

    public void setRegMail(String regMail) {
        this.regMail = regMail;
    }

    public String getRegIP() {
        return regIP;
    }

    public void setRegIP(String regIP) {
        this.regIP = regIP;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPicturePath() {
        return userPicturePath;
    }

    public void setUserPicturePath(String userPicturePath) {
        this.userPicturePath = userPicturePath;
    }

    public String getMaxUserPicturePath() {
        return maxUserPicturePath;
    }

    public void setMaxUserPicturePath(String maxUserPicturePath) {
        this.maxUserPicturePath = maxUserPicturePath;
    }

    public String getMidUserPicturePath() {
        return midUserPicturePath;
    }

    public void setMidUserPicturePath(String midUserPicturePath) {
        this.midUserPicturePath = midUserPicturePath;
    }

    public String getMinUserPicturePath() {
        return minUserPicturePath;
    }

    public void setMinUserPicturePath(String minUserPicturePath) {
        this.minUserPicturePath = minUserPicturePath;
    }

    public BigInteger getPoints() {
        return points;
    }

    public void setPoints(BigInteger points) {
        this.points = points;
    }

    public BigInteger getExp() {
        return exp;
    }

    public void setExp(BigInteger exp) {
        this.exp = exp;
    }

    public String getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(String vipLevel) {
        this.vipLevel = vipLevel;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    private String vipLevel;
    private boolean status;
    private Date createTime;
    private Date lastUpdate;

    private String xinphone;

    private Date vipExpireDate;

    private String vipImage;

    private String vipLevelName;

    private String level;

    private String levelName;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getVipLevelName() {
        return vipLevelName;
    }

    public void setVipLevelName(String vipLevelName) {
        this.vipLevelName = vipLevelName;
    }

    public String getVipImage() {
        return vipImage;
    }

    public void setVipImage(String vipImage) {
        this.vipImage = vipImage;
    }

    public Date getVipExpireDate() {
        return vipExpireDate;
    }

    public void setVipExpireDate(Date vipExpireDate) {
        this.vipExpireDate = vipExpireDate;
    }

    public String getXinphone() {
        return xinphone;
    }

    public void setXinphone(String xinphone) {
        this.xinphone = xinphone;
    }
}
