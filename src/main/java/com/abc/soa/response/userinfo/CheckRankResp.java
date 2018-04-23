package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.CheckInBO;
import com.abc.bean.userinfo.CheckRankBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-24
 * @Time:14:32
 */
public class CheckRankResp extends BaseResponse{
    private List<CheckRankBO> dataList;
    private int total;

    public List<CheckRankBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<CheckRankBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
