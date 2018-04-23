package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.UserExp;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by zlk on 2017-07-26.
 */
public class UserExpResp extends BaseResponse{
    private UserExp data;

    public UserExp getData() {
        return data;
    }

    public void setData(UserExp data) {
        this.data = data;
    }
}
