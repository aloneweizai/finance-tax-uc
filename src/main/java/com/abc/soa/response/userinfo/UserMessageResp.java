package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.UserMessage;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-17
 * @Time:9:46
 */
public class UserMessageResp extends BaseResponse{
    private List<UserMessage> dataList;
    private int total;

    public List<UserMessage> getDataList() {
        return dataList;
    }

    public void setDataList(List<UserMessage> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
