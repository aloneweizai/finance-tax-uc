package com.abc.soa.response;

import com.abc.bean.userinfo.GoodsBO;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/4.
 */
public class InteralGoodsRes extends BaseResponse {
    private GoodsBO data;

    public GoodsBO getData() {
        return data;
    }

    public void setData(GoodsBO data) {
        this.data = data;
    }
}
