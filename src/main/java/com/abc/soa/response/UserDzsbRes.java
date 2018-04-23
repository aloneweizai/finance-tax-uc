package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.request.UserDzsbBo;

import java.util.List;

/**
 * Created by stuy on 2017/7/26.
 */
public class UserDzsbRes extends BaseResponse {

    private List<UserDzsbBo> dataList;

    private int total;

    public List<UserDzsbBo> getDataList() {
        return dataList;
    }

    public void setDataList(List<UserDzsbBo> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
