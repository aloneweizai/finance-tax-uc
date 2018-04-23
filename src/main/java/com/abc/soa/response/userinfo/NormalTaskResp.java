package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.NormalTask;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-09-18
 * @Time:10:10
 */
public class NormalTaskResp extends BaseResponse {
    private List<NormalTask> dataList;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<NormalTask> getDataList() {
        return dataList;
    }

    public void setDataList(List<NormalTask> dataList) {
        this.dataList = dataList;
    }
}
