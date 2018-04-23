package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.ExpLogUcBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by zlk on 2017-08-02.
 */
public class ExpForDateResp extends BaseResponse{
    private List<ExpLogUcBO> dataList;
    private int total;

    public List<ExpLogUcBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<ExpLogUcBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
