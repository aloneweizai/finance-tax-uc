package com.abc.bean.userinfo;

import com.abc.soa.request.PaginationReq;

/**
 * Created by zlk on 2017-08-02.
 */
public class ExpLogDate extends PaginationReq{
    private String start;
    private String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
