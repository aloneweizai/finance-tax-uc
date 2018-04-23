package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.PrecheckBO;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by andy on 2017/11/14.
 */
public class PrecheckBoResp extends BaseResponse{
    private PrecheckBO data;

    public PrecheckBO getData() {
        return data;
    }

    public void setData(PrecheckBO data) {
        this.data = data;
    }
}
