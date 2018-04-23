package com.abc.soa.request;

import java.io.Serializable;


/**
 * 用户订单支付
 **/
@SuppressWarnings("serial")
public class OrderPayBO implements Serializable {

    /**交易流水号*/
    private String tradeNo;
    private String userId;
    /**
     * 是否支付成功，1：支付中，2：支付成功，3：支付失败，
     **/
    private int isPay;
    /**
     * 支付方式：WEIXIN、ALIPAY
     */
    private String payMethod;

    private String tradeMethod;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }


    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }
}
