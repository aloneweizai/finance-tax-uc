package com.abc.soa.request.userinfo;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-09-05
 * @Time:17:27
 */
public class OrderLogReq {
    private String orderNo;
    // 0:订单日志；1：退单日志
    private String logType;
    //退换货id
    private String exchangeId;

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
}
