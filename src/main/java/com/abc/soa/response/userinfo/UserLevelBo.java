package com.abc.soa.response.userinfo;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.LevelBo;

import java.util.List;

/**
 * Created by stuy on 2017/7/19.
 */
public class UserLevelBo extends BaseResponse {
    private List<LevelBo> dataList;

    public List<LevelBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<LevelBo> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    private int total;
}
