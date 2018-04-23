package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;

/**
 * Created by stuy on 2017/8/9.
 */
public class PayRes extends BaseResponse {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
