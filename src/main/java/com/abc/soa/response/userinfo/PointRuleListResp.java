package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.PointRuleBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by andy on 2017/10/24.
 */
public class PointRuleListResp extends BaseResponse{
    private List<PointRuleBO> dataList;
    private int total;

    public List<PointRuleBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<PointRuleBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
