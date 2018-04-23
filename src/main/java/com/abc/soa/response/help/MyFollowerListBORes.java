package com.abc.soa.response.help;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/23 12:04
 */
public class MyFollowerListBORes extends BaseResponse{

    private List<MyFollowerListBO> dataList;

    private long total;

    public List<MyFollowerListBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<MyFollowerListBO> dataList) {
        this.dataList = dataList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
