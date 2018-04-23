package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.ExpRuleBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by andy on 2017/10/24.
 */
public class ExpRuleListResp extends BaseResponse{
    private List<ExpRuleBO> dataList;
    private int total;

    public List<ExpRuleBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<ExpRuleBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
