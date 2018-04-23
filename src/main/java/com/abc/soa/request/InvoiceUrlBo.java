package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/17.
 */
public class InvoiceUrlBo extends BaseResponse {

    private InvoiceDetail data;

    public InvoiceDetail getData() {
        return data;
    }

    public void setData(InvoiceDetail data) {
        this.data = data;
    }
}
