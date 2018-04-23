package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.UgiftApplyBO;
import com.abc.bean.userinfo.UgiftLogBO;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by andy on 2017/12/27.
 */
public class UgiftApplyResp extends BaseResponse{
    private UgiftApplyBO data;

    public UgiftApplyBO getData() {
        return data;
    }

    public void setData(UgiftApplyBO data) {
        this.data = data;
    }
}
