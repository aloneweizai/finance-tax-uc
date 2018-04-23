package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.Gift;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by andy on 2017/12/25.
 */
public class GiftResp extends BaseResponse{
    private Gift data;

    public Gift getData() {
        return data;
    }

    public void setData(Gift data) {
        this.data = data;
    }
}
