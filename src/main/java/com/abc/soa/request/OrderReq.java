package com.abc.soa.request;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by stuy on 2017/7/21.
 */
public class OrderReq {
    private int page;

    private String tradeMethod;

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    private int size;

    private String name;

    private String status;

    private String userId;

    private Boolean isInvoice;


    public Boolean getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Boolean invoice) {
        isInvoice = invoice;
    }
}
