package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.request.UserHngsBo;

import java.util.List;

/**
 * Created by stuy on 2017/7/26.
 */
public class UserHngsRes extends BaseResponse {
    private int total;

    private List<UserHngsBo> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<UserHngsBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<UserHngsBo> dataList) {
        this.dataList = dataList;
    }
}
