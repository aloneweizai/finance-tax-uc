package com.abc.soa.request.userinfo;

/**
 * Created by andy on 2017/12/25.
 */
public class GiftReq {
    private Integer size;

    private Integer page;

    private String name;

    private String category;
    /**状态：0-删除，1-下架，2-上架**/
    private String status;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
