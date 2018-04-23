package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.request.UserDzsbBo;

/**
 * Created by stuy on 2018/1/2.
 */
public class UserDzsbDataBo extends BaseResponse {

    private UserDzsbBo data;

    public UserDzsbBo getData() {
        return data;
    }

    public void setData(UserDzsbBo data) {
        this.data = data;
    }
}
