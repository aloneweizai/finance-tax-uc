package com.abc.soa.request;

import com.abc.bean.userinfo.ProductBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/8/12.
 */
public class IntegralListBo extends BaseResponse{
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<ProductBO> dataList) {
        this.dataList = dataList;
    }

    private List<ProductBO> dataList;
}
