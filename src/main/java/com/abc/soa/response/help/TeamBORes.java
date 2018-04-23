package com.abc.soa.response.help;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/23 12:08
 */
public class TeamBORes extends BaseResponse{

    private List<TeamBO> dataList;

    private long total;

    public List<TeamBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<TeamBO> dataList) {
        this.dataList = dataList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
