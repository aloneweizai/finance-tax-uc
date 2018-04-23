package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/9/22.
 */
public class TaoBaoIpDataBo extends BaseResponse {

    private TaoBaoIpBo data;

    public TaoBaoIpBo getData() {
        return data;
    }

    public void setData(TaoBaoIpBo data) {
        this.data = data;
    }
}
