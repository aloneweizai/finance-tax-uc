package com.abc.bean.userinfo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.request.Invoice;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-25
 * @Time:10:26
 */
public class OrderInvoiceBO extends BaseResponse{
    private Invoice data;

    public Invoice getData() {
        return data;
    }

    public void setData(Invoice data) {
        this.data = data;
    }
}
