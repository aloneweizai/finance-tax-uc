package com.abc.soa.response;

import java.util.Date;

/**
 * Created by stuy on 2018/1/22.
 */
public class CouponBo {

    /**
     * PK
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 优惠劵名称
     */
    private String couponId;

    /**
     * 优惠劵名称
     */
    private String couponName;

    /**
     * 优惠劵模式：固定-FIXED, 浮动-FLOAT
     */
    private String couponMode;

    /**
     * 优惠类型：满减-MANJIAN，立减-LIJIAN，折扣-ZHEKOU
     */
    private String couponType;

    /**
     * 满多少金额
     */
    private Double param1;

    /**
     * 减多少金额、折扣
     */
    private Double param2;

    /**
     * 计算金额类型：订单金额-ORDER, 邮费金额-POSTAGE
     */
    private String amountType;

    /**
     * 有效期起
     */
    private Date validStartTime;

    /**
     * 有效期止
     */
    private Date validEndTime;

    /**
     * 状态: 0-未领取 1-已领取 2-已使用 3-已冻结 4-已删除 5-已过期 6-已作废
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date lastUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCouponMode() {
        return couponMode;
    }

    public void setCouponMode(String couponMode) {
        this.couponMode = couponMode;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Double getParam1() {
        return param1;
    }

    public void setParam1(Double param1) {
        this.param1 = param1;
    }

    public Double getParam2() {
        return param2;
    }

    public void setParam2(Double param2) {
        this.param2 = param2;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public Date getValidStartTime() {
        return validStartTime;
    }

    public void setValidStartTime(Date validStartTime) {
        this.validStartTime = validStartTime;
    }

    public Date getValidEndTime() {
        return validEndTime;
    }

    public void setValidEndTime(Date validEndTime) {
        this.validEndTime = validEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}
