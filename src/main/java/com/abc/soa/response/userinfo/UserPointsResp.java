package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.Points;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by zlk on 2017-07-24.
 */
public class UserPointsResp extends BaseResponse{
    private Points data;

    public Points getData() {
        return data;
    }

    public void setData(Points data) {
        this.data = data;
    }
}
