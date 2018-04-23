package com.abc.soa.response.activity.lottery;

import com.abc.common.soa.response.BaseResponse;


import java.util.List;

public class LotteryActivityRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List< LotteryActivityBO> dataList;
    private  LotteryActivityBO data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List< LotteryActivityBO> getDataList() {
        return dataList;
    }

    public void setDataList(List< LotteryActivityBO> dataList) {
        this.dataList = dataList;
    }

    public  LotteryActivityBO getData() {
        return data;
    }

    public void setData( LotteryActivityBO data) {
        this.data = data;
    }
}
