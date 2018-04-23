package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.CouponUserListBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by andy on 2018/2/7.
 */
public class CouponUserListResp extends BaseResponse{
    private List<CouponUserListBO> dataList;
    private int total;

    public List<CouponUserListBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<CouponUserListBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
