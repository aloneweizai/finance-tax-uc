package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.request.DictBo;

import java.util.List;

/**
 * Created by stuy on 2017/7/27.
 */
public class DictRes extends BaseResponse  {
    private List<DictBo> dataList;

    private int total;

    public List<DictBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<DictBo> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
