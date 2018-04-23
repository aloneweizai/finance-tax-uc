package com.abc.bean.userinfo;

import com.abc.common.soa.response.BaseResponse;

import java.util.Date;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-12
 * @Time:16:08
 */
public class OrderExchange extends BaseResponse{
    /**
     * PK
     **/
    private String id;

    /**
     * FK
     **/
    private String userId;

    /**
     * 订单号
     **/
    private String orderNo;

    /**
     * 换货原因
     **/
    private String reason;

    /**
     * 用户备注
     **/
    private String userRemark;

    /**
     * 管理员备注
     **/
    private String adminRemark;

    /**
     * 用户换货快递单号
     **/
    private String expressNo;

    /**
     * 用户换货快递公司
     **/
    private String expressComp;

    /**
     * 用户换货快递公司
     **/
    private String toExpressComp;
    /**
     * 用户换货快递公司名称
     **/
    private String toExpressCompName;
    /**
     * 用户换货快递单号
     **/
    private String toExpressNo;
    /**
     * 退单状态
     **/
    private String status;

    /****/
    private Date createTime;

    /****/
    private Date lastUpdate;

    // 服务类型 1-换货 2-退货
    private String type;
    // 产品名称
    private String name;
    // 产品图片
    private String imageUrl;
    // 商品类型
    private String goodsType;
    // 用户名
    private String username;
    // 下单时间
    private Date orderTime;
    // 商品数量
    private Integer num;

    //换货人
    private String consignee;
    //换货人号码
    private String contactNumber;
    //换货人地址
    private String shippingAddress;

    public String getToExpressComp() {
        return toExpressComp;
    }

    public void setToExpressComp(String toExpressComp) {
        this.toExpressComp = toExpressComp;
    }

    public String getToExpressCompName() {
        return toExpressCompName;
    }

    public void setToExpressCompName(String toExpressCompName) {
        this.toExpressCompName = toExpressCompName;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressComp() {
        return expressComp;
    }

    public void setExpressComp(String expressComp) {
        this.expressComp = expressComp;
    }

    public String getToExpressNo() {
        return toExpressNo;
    }

    public void setToExpressNo(String toExpressNo) {
        this.toExpressNo = toExpressNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
