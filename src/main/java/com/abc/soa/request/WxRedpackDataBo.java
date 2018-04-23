package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/11/23.
 */
public class WxRedpackDataBo extends BaseResponse {

    private WxRedpackBo data;

    public WxRedpackBo getData() {
        return data;
    }

    public void setData(WxRedpackBo data) {
        this.data = data;
    }
}
