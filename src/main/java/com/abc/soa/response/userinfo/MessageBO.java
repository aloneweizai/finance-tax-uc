package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.BusinessMessage;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-18
 * @Time:9:22
 */
public class MessageBO extends BaseResponse{
    private List<BusinessMessage> data;

    public List<BusinessMessage> getData() {
        return data;
    }

    public void setData(List<BusinessMessage> data) {
        this.data = data;
    }
}
