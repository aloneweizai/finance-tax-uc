package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/7/27.
 */
public class BuMsgRs extends BaseResponse {
    private List<BuMsg> dataList;

    private int total;
    private BuMsg Data;

    public List<BuMsg> getDataList() {
        return dataList;
    }

    public void setDataList(List<BuMsg> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public BuMsg getData() {
        return Data;
    }

    public void setData(BuMsg data) {
        Data = data;
    }
}
