package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2018/1/22.
 */
public class OrderAmountBo extends BaseResponse {

    private Double data;

    public Double getData() {
        return data;
    }

    public void setData(Double data) {
        this.data = data;
    }
}
