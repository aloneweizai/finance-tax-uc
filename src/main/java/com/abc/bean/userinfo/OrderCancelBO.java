package com.abc.bean.userinfo;

/**
 * 用户取消订单Bo
 * Created by zlk on 2017-08-01.
 */
public class OrderCancelBO {
    private String orderNo;
    private String userId;
    private String orderStatus;

    private String remark;

    //取消原因，字典ID
    private String cancelId;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCancelId() {
        return cancelId;
    }

    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }
}
