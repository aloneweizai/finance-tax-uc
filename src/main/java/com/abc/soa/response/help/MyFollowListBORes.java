package com.abc.soa.response.help;

import com.abc.common.soa.response.BaseResponse;
import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/23 12:10
 */
public class MyFollowListBORes extends BaseResponse{

    private List<MyFollowListBO> dataList;

    private long total;

    public List<MyFollowListBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<MyFollowListBO> dataList) {
        this.dataList = dataList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
