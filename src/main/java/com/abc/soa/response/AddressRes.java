package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/7/19.
 */
public class AddressRes extends BaseResponse{
    private List<AddressBo> dataList;

    public List<AddressBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<AddressBo> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;
}
