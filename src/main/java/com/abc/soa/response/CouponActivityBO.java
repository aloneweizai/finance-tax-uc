package com.abc.soa.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 优惠券活动列表对象
 *
 * @author lijun <ljun51@outlook.com>
 * @date 2018-01-13 4:07 PM
 * @since 1.0.0
 */
public class CouponActivityBO {

    /**
     * 优惠劵活动ID
     */
    private String id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 优惠劵ID
     */
    private String couponId;

    /**
     * 优惠劵名称
     */
    private String couponName;

    /**
     * 活动开始时间
     */
    private Date activityStartTime;

    /**
     * 活动截止时间
     */
    private Date activityEndTime;

    /**
     * 优惠劵发放数量
     */
    private Integer couponNum;

    /**
     * 活动状态: 0-删除 1-草稿 2-启用 3-停用
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

    /**
     * 已领取数量
     */
    private Integer collectNum;

    /**
     * 已使用数量
     */
    private Integer usedNum;

    /**
     * 已产生优惠总额
     */
    private Double usedAmount;

    private String imageUrl;

    private Boolean valid;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Integer getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
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

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(Integer usedNum) {
        this.usedNum = usedNum;
    }

    public Double getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(Double usedAmount) {
        this.usedAmount = usedAmount;
    }

    public Boolean getValid() {
        Date now = new Date();
        return now.before(activityEndTime) && now.after(activityStartTime);
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

}
