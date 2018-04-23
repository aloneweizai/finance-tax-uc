package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2018/1/31.
 */
public class CouponActivityDataListBO extends BaseResponse {

    private List<CouponActivityBO> dataList;

    public List<CouponActivityBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<CouponActivityBO> dataList) {
        this.dataList = dataList;
    }
}
