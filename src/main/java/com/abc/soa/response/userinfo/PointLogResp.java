package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.PointLog;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by zlk on 2017-07-20.
 */
public class PointLogResp extends BaseResponse{
    private List<PointLog> dataList;
    private int total;

    public List<PointLog> getDataList() {
        return dataList;
    }

    public void setDataList(List<PointLog> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
