package com.abc.soa.request.userinfo;

import com.abc.soa.request.PaginationReq;

/**
 * Created by zlk on 2017-07-20.
 */
public class PointLogReq extends PaginationReq{
   /* private String name;
    private String code;
    private String type;*/
    private String userId;

   /* public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }*/

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
