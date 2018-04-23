package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/7/19.
 */
public class UserTzxxRes extends BaseResponse {
    private UserTzxxBo data;


    public UserTzxxBo getData() {
        return data;
    }

    public void setData(UserTzxxBo data) {
        this.data = data;
    }
}
