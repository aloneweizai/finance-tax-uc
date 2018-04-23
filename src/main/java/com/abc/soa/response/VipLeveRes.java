package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/2.
 */
public class VipLeveRes extends BaseResponse {
    private VipLevelBo data;

    public VipLevelBo getData() {
        return data;
    }

    public void setData(VipLevelBo data) {
        this.data = data;
    }
}
