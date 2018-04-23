package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/10/26.
 */
public class VipLevelDataBO extends BaseResponse{
    private VipLevelBO data;

    public VipLevelBO getData() {
        return data;
    }

    public void setData(VipLevelBO data) {
        this.data = data;
    }
}
