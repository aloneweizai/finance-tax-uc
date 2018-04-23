package com.abc.soa.request;

/**
 * Created by stuy on 2017/10/26.
 */
public class IntegralBo {

    /**
     * 积分编号
     */
    private String integralId;

    /**
     * 积分金额
     */
    private double fee;

    public String getIntegralId() {
        return integralId;
    }

    public void setIntegralId(String integralId) {
        this.integralId = integralId;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
