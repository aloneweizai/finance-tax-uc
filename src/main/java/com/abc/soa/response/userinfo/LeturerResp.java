package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.FollowLecturerBO;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by andy on 2018/3/14.
 */
public class LeturerResp extends BaseResponse{
    private FollowLecturerBO data;

    public FollowLecturerBO getData() {
        return data;
    }

    public void setData(FollowLecturerBO data) {
        this.data = data;
    }
}
