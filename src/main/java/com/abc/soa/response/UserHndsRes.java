package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.request.UserHndsBo;

import java.util.List;

/**
 * Created by stuy on 2017/7/26.
 */
public class UserHndsRes extends BaseResponse {
    private int total;

    private List<UserHndsBo> dataList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<UserHndsBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<UserHndsBo> dataList) {
        this.dataList = dataList;
    }
}
