package com.abc.bean.userinfo;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by zlk on 2017-07-21.
 */
public class Points extends BaseResponse{
    private Integer myPoints;
    private Integer income;
    private Integer outgo;

    public Integer getMyPoints() {
        return myPoints;
    }

    public void setMyPoints(Integer myPoints) {
        this.myPoints = myPoints;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getOutgo() {
        return outgo;
    }

    public void setOutgo(Integer outgo) {
        this.outgo = outgo;
    }
}
