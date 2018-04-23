package com.abc.soa.response.userinfo;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author:zlk
 * @Description:签到返回参数
 * @Date:2017-08-29
 * @Time:9:30
 */
public class ReCheckBO extends BaseResponse{
    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
