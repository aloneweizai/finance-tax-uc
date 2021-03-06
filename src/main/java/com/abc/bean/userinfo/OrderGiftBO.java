package com.abc.bean.userinfo;

/**
 * 订单赠送权益
 * Created by andy on 2018/3/22.
 */
public class OrderGiftBO {

    /**主键**/
    private String id;

    /**关联uc_order的主键**/
    private String orderNo;

    /**操作符号：or-或,and-和**/
    private String operSymbol;

    /**操作类型：POINTS-积分，COUPON-优惠券，VIP-会员，AMOUNT-礼包金额**/
    private String operType;

    /**操作值**/
    private String operValue;



    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setOperSymbol(String operSymbol){
        this.operSymbol = operSymbol;
    }

    public String getOperSymbol(){
        return this.operSymbol;
    }

    public void setOperType(String operType){
        this.operType = operType;
    }

    public String getOperType(){
        return this.operType;
    }

    public void setOperValue(String operValue){
        this.operValue = operValue;
    }

    public String getOperValue(){
        return this.operValue;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
