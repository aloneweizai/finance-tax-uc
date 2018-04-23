package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.request.SwUserBangDingBo;

import java.util.List;

/**
 * Created by stuy on 2017/7/25.
 */
public class SwUserRes extends BaseResponse {

    private List<SwUserBangDingBo> dataList;

    private int  total;

    public List<SwUserBangDingBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<SwUserBangDingBo> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
