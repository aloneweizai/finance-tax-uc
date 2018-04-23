package com.abc.soa.request.userinfo;

import com.abc.soa.request.PaginationReq;

/**
 * Created by andy on 2018/1/22.
 */
public class CouponsReq extends PaginationReq {
    private String status;
    private String categoryIds;
    //0：查全部，1：查过期，2：查未过期
    private String isDate  ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getIsDate() {
        return isDate;
    }

    public void setIsDate(String isDate) {
        this.isDate = isDate;
    }
}
