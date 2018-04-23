package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.UserBo;

/**
 * Created by stuy on 2017/8/15.
 */
public class UserIdBo extends BaseResponse {
    private UserBo data;

    public UserBo getData() {
        return data;
    }

    public void setData(UserBo data) {
        this.data = data;
    }
}
