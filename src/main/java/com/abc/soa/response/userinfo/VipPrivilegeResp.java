package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.VipPrivilege;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-12
 * @Time:17:22
 */
public class VipPrivilegeResp extends BaseResponse {
    private List<VipPrivilege> dataList;
    private int total;

    public List<VipPrivilege> getDataList() {
        return dataList;
    }

    public void setDataList(List<VipPrivilege> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
