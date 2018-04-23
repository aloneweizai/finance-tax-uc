package com.abc.soa.response.userinfo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.UserTzxxBo;

/**
 * Created by 128 on 2017/7/19.
 */
public class UserInfoBo extends BaseResponse {

    private UserBo user;
    private UserTzxxBo user_extend;

    public UserBo getUser() {
        return user;
    }

    public void setUser(UserBo user) {
        this.user = user;
    }

    public UserTzxxBo getUser_extend() {
        return user_extend;
    }

    public void setUser_extend(UserTzxxBo user_extend) {
        this.user_extend = user_extend;
    }
}
