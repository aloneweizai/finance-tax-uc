package com.abc.soa.response.region;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/6/13 17:22
 */
public class CityListRs extends BaseResponse {

    private List<City> dataList;

    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<City> getDataList() {
        return dataList;
    }

    public void setDataList(List<City> dataList) {
        this.dataList = dataList;
    }
}
