package com.abc.bean.userinfo;

import java.util.Date;
import java.util.List;

/**
 * Created by andy on 2017/10/26.
 */
public class TradeBO {
    private String tradeNo;
    private String orderNo;
    private Date createTime;
    private Date lastUpdate;
    private List<TradeLogBO> tradeLogBOList;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<TradeLogBO> getTradeLogBOList() {
        return tradeLogBOList;
    }

    public void setTradeLogBOList(List<TradeLogBO> tradeLogBOList) {
        this.tradeLogBOList = tradeLogBOList;
    }
}
