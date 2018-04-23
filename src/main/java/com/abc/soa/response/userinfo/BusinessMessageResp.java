package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.BusinessMessage;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-16
 * @Time:20:46
 */
public class BusinessMessageResp extends BaseResponse{
    private List<BusinessMessage> dataList;
    private int total;

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
}
