package com.abc.soa.response;

import com.abc.bean.userinfo.GoodsBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by stuy on 2017/8/3.
 */
public class GoodsRes extends BaseResponse {

    private List<GoodsBO> dataList;

    private int total;

    public List<GoodsBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<GoodsBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
