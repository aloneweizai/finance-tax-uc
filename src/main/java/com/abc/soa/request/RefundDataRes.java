package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/7.
 */
public class RefundDataRes extends BaseResponse {

    private PayqueryRes data;

    public PayqueryRes getData() {
        return data;
    }

    public void setData(PayqueryRes data) {
        this.data = data;
    }
}
