package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.Gift;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by andy on 2017/12/25.
 */
public class GiftListResp extends BaseResponse{
    private int total;
    private List<Gift> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Gift> getDataList() {
        return dataList;
    }

    public void setDataList(List<Gift> dataList) {
        this.dataList = dataList;
    }
}
