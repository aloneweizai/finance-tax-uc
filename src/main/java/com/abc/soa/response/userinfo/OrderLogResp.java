package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.OrderLogBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-16
 * @Time:17:55
 */
public class OrderLogResp extends BaseResponse{
    private List<OrderLogBO> dataList;
    private int total;

    public List<OrderLogBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<OrderLogBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
