package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.Subscriptions;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by andy on 2018/3/5.
 */
public class SubscriptionResp extends BaseResponse{
    private List<Subscriptions> dataList;
    private int total;

    public List<Subscriptions> getDataList() {
        return dataList;
    }

    public void setDataList(List<Subscriptions> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
