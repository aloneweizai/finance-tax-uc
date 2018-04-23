package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.request.UserHndsBo;
import com.abc.soa.request.UserHngsBo;

/**
 * Created by stuy on 2018/1/2.
 */
public class UserHngsDataBo extends BaseResponse {

    private UserHngsBo data;

    public UserHngsBo getData() {
        return data;
    }

    public void setData(UserHngsBo data) {
        this.data = data;
    }
}
