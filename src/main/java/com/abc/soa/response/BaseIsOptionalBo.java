package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2018/3/22.
 */
public class BaseIsOptionalBo extends BaseResponse {

    private Boolean data;

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }
}
