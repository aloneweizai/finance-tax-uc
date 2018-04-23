package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.ExpressComp;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author:zlk
 * @Description:
 * @Date:2017-09-13
 * @Time:11:34
 */
public class ExpressCompResp extends BaseResponse{
    private ExpressComp data;

    public ExpressComp getData() {
        return data;
    }

    public void setData(ExpressComp data) {
        this.data = data;
    }
}
