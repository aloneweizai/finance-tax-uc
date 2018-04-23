package com.abc.soa.response;

import com.abc.bean.userinfo.BusinessMessage;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/7/27.
 */
public class BusinessMessageRs extends BaseResponse {
    private List<BusinessMessage> dataList;

    private int total;
    private BusinessMessage Data;

    public List<BusinessMessage> getDataList() {
        return dataList;
    }

    public void setDataList(List<BusinessMessage> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public BusinessMessage getData() {
        return Data;
    }

    public void setData(BusinessMessage data) {
        Data = data;
    }
}
