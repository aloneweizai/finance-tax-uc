package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/7/27.
 */
public class IntRs extends BaseResponse {

    public Integer getData() {
        return Data;
    }

    public void setData(Integer data) {
        Data = data;
    }

    private Integer Data;

}
