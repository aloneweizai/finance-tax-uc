package com.abc.soa.request.userinfo;

/**
 * Created by andy on 2017/12/25.
 */
public class UgiftApplyListReq {
    private Integer page;
    private Integer size;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
