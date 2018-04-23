package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.UserBo;

/**
 * Created by stuy on 2017/9/11.
 */
public class UserSlatBo extends BaseResponse {
    private UserBo data;

    private UserBo user;

    public UserBo getUser() {
        return user;
    }

    public void setUser(UserBo user) {
        this.user = user;
    }

    public UserBo getData() {
        return data;
    }

    public void setData(UserBo data) {
        this.data = data;
    }
}
