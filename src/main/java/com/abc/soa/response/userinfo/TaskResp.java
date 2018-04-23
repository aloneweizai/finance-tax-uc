package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.SysTask;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by Administrator on 2017-07-19.
 */
public class TaskResp extends BaseResponse{
    private List<SysTask> dataList;
    private int Total;

    public List<SysTask> getDataList() {
        return dataList;
    }

    public void setDataList(List<SysTask> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }
}
