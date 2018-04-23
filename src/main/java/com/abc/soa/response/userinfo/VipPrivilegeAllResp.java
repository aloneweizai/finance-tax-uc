package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.VipPrivilegeLevel;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-17
 * @Time:16:13
 */
public class VipPrivilegeAllResp extends BaseResponse{
    private List<List<VipPrivilegeLevel>> dataList;
    private int total;

    public List<List<VipPrivilegeLevel>> getDataList() {
        return dataList;
    }

    public void setDataList(List<List<VipPrivilegeLevel>> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
