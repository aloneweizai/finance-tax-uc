package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/9/22.
 */
public class WxCallbackBo extends BaseResponse {
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
