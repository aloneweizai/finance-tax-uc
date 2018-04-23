package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/7/20.
 */
public class AddressIDRes extends BaseResponse {
    private AddressBo data;

    public AddressBo getData() {
        return data;
    }

    public void setData(AddressBo data) {
        this.data = data;
    }
}
