package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by Administrator on 2017-11-16.
 */
public class PhoneBo extends BaseResponse {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
