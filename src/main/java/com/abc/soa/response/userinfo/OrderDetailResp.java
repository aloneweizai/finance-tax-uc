package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.OrderBO;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by zlk on 2017-08-01.
 */
public class OrderDetailResp extends BaseResponse{
    private OrderBO data;

    public OrderBO getData() {
        return data;
    }

    public void setData(OrderBO data) {
        this.data = data;
    }
}
