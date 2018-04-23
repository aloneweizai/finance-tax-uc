package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2018/1/31.
 */
public class CouponDataBo extends BaseResponse {

    private Coupon data;

    public Coupon getData() {
        return data;
    }

    public void setData(Coupon data) {
        this.data = data;
    }
}
