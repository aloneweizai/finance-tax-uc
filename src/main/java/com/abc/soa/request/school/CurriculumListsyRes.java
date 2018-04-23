package com.abc.soa.request.school;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/8/30.
 */
public class CurriculumListsyRes extends BaseResponse {

    private List<CurriculumListsyBo> dataList;

    public List<CurriculumListsyBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CurriculumListsyBo> dataList) {
        this.dataList = dataList;
    }
}
