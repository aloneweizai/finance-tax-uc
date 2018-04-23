package com.abc.soa.response.userinfo;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by andy on 2017/11/7.
 * 累计签到
 */
public class CheckTotalResp extends BaseResponse {
    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
