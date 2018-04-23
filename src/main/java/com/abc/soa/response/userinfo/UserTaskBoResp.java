package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.UserTaskBO;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by zlk on 2017-07-24.
 */
public class UserTaskBoResp extends BaseResponse{
    private UserTaskBO data;

    public UserTaskBO getData() {
        return data;
    }

    public void setData(UserTaskBO data) {
        this.data = data;
    }
}
