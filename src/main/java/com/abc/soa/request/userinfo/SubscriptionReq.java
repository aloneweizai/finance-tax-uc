package com.abc.soa.request.userinfo;

import com.abc.soa.request.PaginationReq;

/**
 * Created by andy on 2018/3/5.
 */
public class SubscriptionReq extends PaginationReq{
    private String type;
    private String busiType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }
}
