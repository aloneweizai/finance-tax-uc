package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/8/12.
 */
public class DictListBo extends BaseResponse {

    private List<DictBo> dataList;

    public List<DictBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<DictBo> dataList) {
        this.dataList = dataList;
    }
}
