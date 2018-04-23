package com.abc.soa.request.school;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/8/30.
 */
public class CurrMyStudyRes extends BaseResponse {

    private List<CurrMyStudyBo> dataList;

    public List<CurrMyStudyBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CurrMyStudyBo> dataList) {
        this.dataList = dataList;
    }
}
