package com.abc.soa.response.activity.lottery;

import com.abc.common.soa.response.BaseResponse;


import java.util.List;

public class LotteryLogRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<LotteryLogBO> dataList;
    private LotteryLogBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List<LotteryLogBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<LotteryLogBO> dataList) {
        this.dataList = dataList;
    }

    public LotteryLogBO getData() {
        return data;
    }

    public void setData(LotteryLogBO data) {
        this.data = data;
    }
}
