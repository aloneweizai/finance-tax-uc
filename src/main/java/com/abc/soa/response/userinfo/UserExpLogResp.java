package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.ExpLog;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by zlk on 2017-07-24.
 */
public class UserExpLogResp extends BaseResponse {
    private List<ExpLog> dataList;
    private int total;

    public List<ExpLog> getDataList() {
        return dataList;
    }

    public void setDataList(List<ExpLog> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
