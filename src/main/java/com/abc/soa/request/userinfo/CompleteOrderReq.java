package com.abc.soa.request.userinfo;

import com.abc.soa.request.PaginationReq;

/**
 * @Author:zlk
 * @Description:已完成订单
 * @Date:2017-08-18
 * @Time:16:00
 */
public class CompleteOrderReq extends PaginationReq{
    private String name;
    private String userId;
    //添加订单交易方式
    private String status;
    private String tradeMethod;

    private String startTime;
    private String endTime;

    //是否开发票
    private Boolean isInvoice;
    //可否退换：true可以；false不可以
    private Boolean isReturn;

    public Boolean getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Boolean isReturn) {
        this.isReturn = isReturn;
    }

    public Boolean getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Boolean isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
