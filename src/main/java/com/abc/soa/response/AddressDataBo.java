package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2018/1/30.
 */
public class AddressDataBo extends BaseResponse {

    private AddressBo data;

    public AddressBo getData() {
        return data;
    }

    public void setData(AddressBo data) {
        this.data = data;
    }
}
