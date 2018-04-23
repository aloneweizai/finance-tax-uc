package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.EarnedBO;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by andy on 2017/10/27.
 */
public class EarnedBoResp extends BaseResponse {
    private EarnedBO data;

    public EarnedBO getData() {
        return data;
    }

    public void setData(EarnedBO data) {
        this.data = data;
    }
}
