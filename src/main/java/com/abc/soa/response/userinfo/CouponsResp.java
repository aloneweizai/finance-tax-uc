package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.CouponBo;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by andy on 2018/1/22.
 */
public class CouponsResp extends BaseResponse{
    private List<CouponBo> dataList;
    private int total;

    public List<CouponBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<CouponBo> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
