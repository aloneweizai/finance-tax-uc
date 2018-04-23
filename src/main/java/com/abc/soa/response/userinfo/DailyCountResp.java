package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.DailyTask;
import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by zlk on 2017-07-26.
 */
public class DailyCountResp extends BaseResponse{
    private List<DailyTask> dataList;

    public List<DailyTask> getDataList() {
        return dataList;
    }

    public void setDataList(List<DailyTask> dataList) {
        this.dataList = dataList;
    }
}
