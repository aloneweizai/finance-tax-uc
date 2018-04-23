package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.request.UserHndsBo;

/**
 * Created by stuy on 2018/1/2.
 */
public class UserHndsDataBo extends BaseResponse {

    private UserHndsBo data;

    public UserHndsBo getData() {
        return data;
    }

    public void setData(UserHndsBo data) {
        this.data = data;
    }
}
