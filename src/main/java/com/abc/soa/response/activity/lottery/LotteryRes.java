package com.abc.soa.response.activity.lottery;

import com.abc.common.soa.response.BaseResponse;

/**
 * @Author liuQi
 * @Date 2017/9/9 10:39
 */
public class LotteryRes extends BaseResponse{

    private Lottery data;

    public Lottery getData() {
        return data;
    }

    public LotteryRes setData(Lottery data) {
        this.data = data;
        return this;
    }
}
