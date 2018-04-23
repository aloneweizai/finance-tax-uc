package com.abc.soa.request;

/**
 * Created by stuy on 2018/3/21.
 */
public class CurriculumGiftBo {

    /**主键**/
    private String id;

    /**关联bb_curriculum_uvip_price的主键**/
    private String giftId;

    /**操作符号：or-或,and-和**/
    private String operSymbol;

    /**操作类型：POINTS-积分，COUPON-优惠券，VIP-会员，AMOUNT-礼包金额**/
    private String operType;

    /**操作值**/
    private String operValue;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getOperSymbol() {
        return operSymbol;
    }

    public void setOperSymbol(String operSymbol) {
        this.operSymbol = operSymbol;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperValue() {
        return operValue;
    }

    public void setOperValue(String operValue) {
        this.operValue = operValue;
    }
}
