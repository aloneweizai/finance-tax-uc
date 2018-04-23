package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/10/26.
 */
public class VipLevelDataListBO extends BaseResponse {

    private List<VipLevelBO> dataList;

    public List<VipLevelBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<VipLevelBO> dataList) {
        this.dataList = dataList;
    }
}
