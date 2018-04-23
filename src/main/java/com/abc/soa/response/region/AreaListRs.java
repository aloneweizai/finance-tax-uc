package com.abc.soa.response.region;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/6/13 17:23
 */
public class AreaListRs extends BaseResponse {

    private List<Area> dataList;

    private int total;

    public List<Area> getDataList() {
        return dataList;
    }

    public void setDataList(List<Area> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
