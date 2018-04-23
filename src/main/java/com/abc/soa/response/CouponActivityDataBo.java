package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2018/1/31.
 */
public class CouponActivityDataBo extends BaseResponse {

    private CouponActivity data;

    public CouponActivity getData() {
        return data;
    }

    public void setData(CouponActivity data) {
        this.data = data;
    }
}
