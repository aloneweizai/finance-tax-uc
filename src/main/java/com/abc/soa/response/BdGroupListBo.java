package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2018/4/10.
 */
public class BdGroupListBo extends BaseResponse {

    private List<BdGroupBo> dataList;

    public List<BdGroupBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<BdGroupBo> dataList) {
        this.dataList = dataList;
    }
}
