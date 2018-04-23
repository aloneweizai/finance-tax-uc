package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/5.
 */
public class LotteryLogRs extends BaseResponse {

    private LotteryLogBO data;

    public LotteryLogBO getData() {
        return data;
    }

    public void setData(LotteryLogBO data) {
        this.data = data;
    }
}
