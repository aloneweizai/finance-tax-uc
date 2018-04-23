package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.OrderExchange;
import com.abc.common.soa.response.BaseResponse;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-15
 * @Time:17:41
 */
public class OrderExchangeBO extends BaseResponse{
    private OrderExchange data;

    public OrderExchange getData() {
        return data;
    }

    public void setData(OrderExchange data) {
        this.data = data;
    }
}
