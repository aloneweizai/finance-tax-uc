package com.abc.soa.request.school;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/30.
 */
public class CurrMyStudyNumRes extends BaseResponse {
    private CurrMyStudyNumBo data;

    public CurrMyStudyNumBo getData() {
        return data;
    }

    public void setData(CurrMyStudyNumBo data) {
        this.data = data;
    }
}
