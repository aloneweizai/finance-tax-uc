package com.abc.bean.userinfo;

/**
 * @Author:zlk
 * @Description:订单产品规格
 * @Date:2017-08-25
 * @Time:11:02
 */
public class OrderProductSpec {
    /**
     * FK
     **/
    private String orderNo;

    /**
     * FK
     **/
    private String productId;

    /**
     * FK
     **/
    private String specId;

    private String specName;

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSpecId() {
        return this.specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }
}
