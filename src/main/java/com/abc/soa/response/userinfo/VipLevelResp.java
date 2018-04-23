package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.VipLevel;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-15
 * @Time:10:27
 */
public class VipLevelResp extends BaseResponse{
    private List<VipLevel> dataList;
    private int total;

    public List<VipLevel> getDataList() {
        return dataList;
    }

    public void setDataList(List<VipLevel> dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
