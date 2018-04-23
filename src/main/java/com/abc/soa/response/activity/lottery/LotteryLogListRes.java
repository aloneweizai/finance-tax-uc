package com.abc.soa.response.activity.lottery;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/9 15:07
 */
public class LotteryLogListRes extends BaseResponse{

    private List<LotteryLog> dataList;

    private int total;

    public int getTotal() {
        return total;
    }

    public LotteryLogListRes setTotal(int total) {
        this.total = total;
        return this;
    }

    public List<LotteryLog> getDataList() {
        return dataList;
    }

    public LotteryLogListRes setDataList(List<LotteryLog> dataList) {
        this.dataList = dataList;
        return this;
    }
}
