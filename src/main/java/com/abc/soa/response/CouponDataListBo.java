package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2018/1/22.
 */
public class CouponDataListBo extends BaseResponse{

    private List<CouponBo> dataList;

    public List<CouponBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CouponBo> dataList) {
        this.dataList = dataList;
    }
}
