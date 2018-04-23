package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/5.
 */
public class LotteryReturnRs extends BaseResponse {

    private LotteryReturnBO data;

    public LotteryReturnBO getData() {
        return data;
    }

    public void setData(LotteryReturnBO data) {
        this.data = data;
    }
}
