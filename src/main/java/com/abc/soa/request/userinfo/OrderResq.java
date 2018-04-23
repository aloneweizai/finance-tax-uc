package com.abc.soa.request.userinfo;

import com.abc.bean.userinfo.OrderBO;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/5.
 */
public class OrderResq extends BaseResponse {
    private OrderBO data;

    public OrderBO getData() {
        return data;
    }

    public void setData(OrderBO data) {
        this.data = data;
    }
}
