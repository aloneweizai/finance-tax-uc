package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.GoodsBO;
import com.abc.common.soa.response.BaseResponse;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-25
 * @Time:17:13
 */
public class GoodsBoResp extends BaseResponse{
    private GoodsBO data;

    public GoodsBO getData() {
        return data;
    }

    public void setData(GoodsBO data) {
        this.data = data;
    }
}
