package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/8/10.
 */
public class CodeTimeBo extends BaseResponse{
    private String code;

    private long time;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
