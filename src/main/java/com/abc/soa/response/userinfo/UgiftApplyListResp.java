package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.UgiftApplyBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by andy on 2017/12/25.
 */
public class UgiftApplyListResp extends BaseResponse {
    private int total;
    private List<UgiftApplyBO> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<UgiftApplyBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<UgiftApplyBO> dataList) {
        this.dataList = dataList;
    }
}
