package com.abc.soa.request;

import java.util.List;

/**
 * Created by stuy on 2017/10/26.
 */
public class NewOrderBo  {
    /**
     * 用户编号
     * 必填
     */
    private String userId;
    /**
     * 交易方式
     * 非必填
     */
    private String deliveryMethod;
    /**
     * 用户名称
     * 必填
     */
    private String username;

    /**
     * 必填,1不寄送；2寄送
     */
    private int isShipping = 2;
    /**
     * 必填
     */
    private int isFreeShipping = 2;
    /**
     * 快递费
     * 非必填
     */
    private double deliveryFee;
    /**
     * 总价
     * 必填
     */
    private double totalPrice;
    /**
     * 备注
     * 非必填
     */
    private String remark;
    /**
     * 当前会员等级
     * 必填
     */
    private String nowVipLevel;
    /**
     * 货币类型RMB或积分
     * 必填
     */
    private String tradeMethod;
    /**
     * 介绍人
     * 非必填
     */
    private String recommendUser;

    /**
     * 赠送积分
     */
    private Integer giftPoints;

    /**
     * 优惠券
     */
    private String useCouponId;

    //下单人
    private String consignee;
    //下单人号码
    private String contactNumber;
    //下单人地址
    private String shippingAddress;

    private String pointsPrice;

    public String getPointsPrice() {
        return pointsPrice;
    }

    public void setPointsPrice(String pointsPrice) {
        this.pointsPrice = pointsPrice;
    }

    private List<CurriculumGiftBo> orderGiftBOList;

    public List<CurriculumGiftBo> getOrderGiftBOList() {
        return orderGiftBOList;
    }

    public void setOrderGiftBOList(List<CurriculumGiftBo> orderGiftBOList) {
        this.orderGiftBOList = orderGiftBOList;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getUseCouponId() {
        return useCouponId;
    }

    public void setUseCouponId(String useCouponId) {
        this.useCouponId = useCouponId;
    }

    public Integer getGiftPoints() {
        return giftPoints;
    }

    public void setGiftPoints(Integer giftPoints) {
        this.giftPoints = giftPoints;
    }

    /**
     * 商品详情
     * 必填
     */
    private List<orderProductBO> orderProductBOList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIsShipping() {
        return isShipping;
    }

    public void setIsShipping(int isShipping) {
        this.isShipping = isShipping;
    }

    public int getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(int isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNowVipLevel() {
        return nowVipLevel;
    }

    public void setNowVipLevel(String nowVipLevel) {
        this.nowVipLevel = nowVipLevel;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public String getRecommendUser() {
        return recommendUser;
    }

    public void setRecommendUser(String recommendUser) {
        this.recommendUser = recommendUser;
    }

    public List<orderProductBO> getOrderProductBOList() {
        return orderProductBOList;
    }

    public void setOrderProductBOList(List<orderProductBO> orderProductBOList) {
        this.orderProductBOList = orderProductBOList;
    }

}
