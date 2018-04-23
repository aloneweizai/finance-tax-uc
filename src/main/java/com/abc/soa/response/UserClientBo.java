package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/11.
 */
public class UserClientBo extends BaseResponse {

    private UserBo data;

    public UserBo getData() {
        return data;
    }

    public void setData(UserBo data) {
        this.data = data;
    }
}
