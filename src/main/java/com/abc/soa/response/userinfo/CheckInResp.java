package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.CheckInBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-22
 * @Time:17:23
 */
public class CheckInResp extends BaseResponse{
    private List<CheckInBO> dataList;
    private int total;

    public List<CheckInBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<CheckInBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
