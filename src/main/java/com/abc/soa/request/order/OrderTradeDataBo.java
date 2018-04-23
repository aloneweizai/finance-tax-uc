package com.abc.soa.request.order;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/10/27.
 */
public class OrderTradeDataBo extends BaseResponse {

    private OrderTradeBo data;

    public OrderTradeBo getData() {
        return data;
    }

    public void setData(OrderTradeBo data) {
        this.data = data;
    }
}
