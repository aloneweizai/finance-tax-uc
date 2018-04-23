package com.abc.soa.response.help;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/23 12:01
 */
public class MyCollectListBORes extends BaseResponse{

    private List<MyCollectListBO> dataList;

    private long total;

    public List<MyCollectListBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<MyCollectListBO> dataList) {
        this.dataList = dataList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
