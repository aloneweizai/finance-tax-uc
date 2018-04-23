package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/7/19.
 */
public class InvoiceRes extends BaseResponse {
    private List<InvoiceBo> dataList;

    public List<InvoiceBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<InvoiceBo> dataList) {
        this.dataList = dataList;
    }

//    private String dataList;
//
//    public String getDataList() {
//        return dataList;
//    }
//
//    public void setDataList(String dataList) {
//        this.dataList = dataList;
//    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;
}
