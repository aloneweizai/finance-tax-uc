package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/9/8.
 */
public class PhoneForgotBo extends BaseResponse {
    private UserBo data;

    public UserBo getData() {
        return data;
    }

    public void setData(UserBo data) {
        this.data = data;
    }
}
