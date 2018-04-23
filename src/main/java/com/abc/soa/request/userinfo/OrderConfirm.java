package com.abc.soa.request.userinfo;

/**
 * @Author:zlk
 * @Description:确认收货
 * @Date:2017-09-01
 * @Time:16:02
 */
public class OrderConfirm {
    private String orderNo;
    private String userId;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
