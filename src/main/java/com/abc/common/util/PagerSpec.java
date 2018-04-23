package com.abc.common.util;

/**
 * @Author liuqi
 * @Date 2017/5/12 11:47
 */
public class PagerSpec{

    private int currentPage;    //当前页

    private long totalItems;    //共多少条

    private int perPageNum;     //每页条数

    private int totalPage;      //总页数

    private int offset;         //每页起始

    private int navOffsest;     //分页导航起始

    private String link;         //链接

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getPerPageNum() {
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        this.perPageNum = perPageNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNavOffsest() {
        return navOffsest;
    }

    public void setNavOffsest(int navOffsest) {
        this.navOffsest = navOffsest;
    }
}
