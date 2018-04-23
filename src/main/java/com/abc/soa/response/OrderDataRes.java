package com.abc.soa.response;

import com.abc.bean.userinfo.OrderBO;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/4.
 */
public class OrderDataRes extends BaseResponse {
    private OrderBO data;

    public OrderBO getData() {
        return data;
    }

    public void setData(OrderBO data) {
        this.data = data;
    }
}
