package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.FollowLecturerBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by andy on 2018/3/13.
 */
public class LecturerListResp extends BaseResponse{
    private List<FollowLecturerBO> dataList;
    private int total;

    public List<FollowLecturerBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<FollowLecturerBO> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
