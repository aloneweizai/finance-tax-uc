package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2018/4/9.
 */
public class WeixinBo extends BaseResponse{


    private WeiXinSoaBo data;

    public WeiXinSoaBo getData() {
        return data;
    }

    public void setData(WeiXinSoaBo data) {
        this.data = data;
    }
}
