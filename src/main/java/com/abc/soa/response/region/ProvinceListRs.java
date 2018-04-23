package com.abc.soa.response.region;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/6/13 17:19
 */
public class ProvinceListRs extends BaseResponse {

    private List<Province> dataList;

    private int total;


    public List<Province> getDataList() {
        return dataList;
    }

    public void setDataList(List<Province> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
