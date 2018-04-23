package com.abc.soa.request.userinfo;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-17
 * @Time:16:12
 */
public class VipPrivilegeAllReq {
    private Boolean status;
    private Integer page;
    private Integer size;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
