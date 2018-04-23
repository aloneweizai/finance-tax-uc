package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2018/4/9.
 */
public class WeixinQueryBo extends BaseResponse{

    private WxQueryBo data;

    public WxQueryBo getData() {
        return data;
    }

    public void setData(WxQueryBo data) {
        this.data = data;
    }
}
