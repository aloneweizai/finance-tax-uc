package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.UvipPrice;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-09-21
 * @Time:18:23
 */
public class UvipPriceResp extends BaseResponse {
    private List<UvipPrice> dataList;
    private UvipPrice data;
    private int total;

    public List<UvipPrice> getDataList() {
        return dataList;
    }

    public void setDataList(List<UvipPrice> dataList) {
        this.dataList = dataList;
    }

    public UvipPrice getData() {
        return data;
    }

    public void setData(UvipPrice data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
