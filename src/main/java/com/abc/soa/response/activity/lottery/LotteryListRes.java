package com.abc.soa.response.activity.lottery;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuQi
 * @Date 2017/9/8 17:53
 */
public class LotteryListRes extends BaseResponse{

    private List<Lottery> dataList;

    private int total;

    public List<Lottery> getDataList() {
        return dataList;
    }

    public LotteryListRes setDataList(List<Lottery> dataList) {
        this.dataList = dataList;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public LotteryListRes setTotal(int total) {
        this.total = total;
        return this;
    }
}



