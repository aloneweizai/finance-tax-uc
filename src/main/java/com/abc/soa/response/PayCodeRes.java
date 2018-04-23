package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/5.
 */
public class PayCodeRes extends BaseResponse {

    private PayCodeRsp data;

    public PayCodeRsp getData() {
        return data;
    }

    public void setData(PayCodeRsp data) {
        this.data = data;
    }
}
