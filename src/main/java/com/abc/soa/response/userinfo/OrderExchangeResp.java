package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.OrderExchange;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-12
 * @Time:16:07
 */
public class OrderExchangeResp extends BaseResponse{
    private List<OrderExchange> dataList;
    private int total;

    public List<OrderExchange> getDataList() {
        return dataList;
    }

    public void setDataList(List<OrderExchange> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
