package com.abc.soa.request;

/**
 * Created by stuy on 2017/8/7.
 */
public class Fundbill {
    private String amount;//渠道实际付款金额
    private String fund_channel;//交易使用的资金渠道

    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getFund_channel() {
        return fund_channel;
    }
    public void setFund_channel(String fund_channel) {
        this.fund_channel = fund_channel;
    }
}
