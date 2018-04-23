package com.abc.soa.request;

/**
 * @Author liuqi
 * @Date 2017/7/23 17:14
 */
public class PaginationReq {

    private Integer page;

    private Integer size;

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

    public PaginationReq(){}

    public PaginationReq(int page, int size){
        this.page = page;
        this.size = size;
    }

}
