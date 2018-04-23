package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.PointsLogUcBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-03
 * @Time:11:56
 */
public class PointsForDateResp extends BaseResponse{
    private List<PointsLogUcBO> dataList;
    private int total;

    public List<PointsLogUcBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<PointsLogUcBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
