package com.abc.soa.response.userinfo;

import com.abc.common.soa.response.BaseResponse;

/**
 *
 * Created by zlk on 2017-07-18.
 */
public class OrderListResp extends BaseResponse{
    private String dataList;
    private int total;

    public String getDataList() {
        return dataList;
    }

    public void setDataList(String dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
