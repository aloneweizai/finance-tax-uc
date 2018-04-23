package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.VipPrivilege;
import com.abc.common.soa.response.BaseResponse;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-08-14
 * @Time:14:32
 */
public class VipPrivilegeBO extends BaseResponse{
    private VipPrivilege data;

    public VipPrivilege getData() {
        return data;
    }

    public void setData(VipPrivilege data) {
        this.data = data;
    }
}
