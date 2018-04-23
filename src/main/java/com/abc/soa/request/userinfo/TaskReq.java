package com.abc.soa.request.userinfo;

import com.abc.soa.request.PaginationReq;

/**
 * Created by zlk on 2017-07-19.
 */
public class TaskReq extends PaginationReq {
    private String name;
    private String type;
    private String status;
    private String userId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
