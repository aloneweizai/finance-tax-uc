package com.abc.soa.response.activity.lottery;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

public class LotteryTemplateExRs extends BaseResponse {

    /**
     *
     */
    private static final long serialVersionUID = -7859370887990688693L;
    private int total;

    private List<TemplateBo> dataList;
    private  TemplateBo data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public List< TemplateBo> getDataList() {
        return dataList;
    }

    public void setDataList(List< TemplateBo> dataList) {
        this.dataList = dataList;
    }

    public  TemplateBo getData() {
        return data;
    }

    public void setData( TemplateBo data) {
        this.data = data;
    }
}
