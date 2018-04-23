package com.abc.soa.response.userinfo;

import com.abc.bean.userinfo.Event;
import com.abc.bean.userinfo.EventBO;
import com.abc.common.soa.response.BaseResponse;

/**
 * Created by andy on 2017/9/29.
 */
public class EventResp extends BaseResponse{
    private Event data;

    public Event getData() {
        return data;
    }

    public void setData(Event data) {
        this.data = data;
    }
}
