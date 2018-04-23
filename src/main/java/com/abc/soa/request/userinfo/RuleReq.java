package com.abc.soa.request.userinfo;

import com.abc.soa.request.PaginationReq;

/**
 * Created by andy on 2017/11/11.
 */
public class RuleReq extends PaginationReq {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
