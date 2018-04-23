package com.abc.soa.response.help;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/7/28 12:02
 */
public class LetterListBOListRes extends BaseResponse {


    private List<LetterListBO> dataList;

    private long total;

    public List<LetterListBO> getDataList() {
        return dataList;
    }

    public void setDataList(List<LetterListBO> dataList) {
        this.dataList = dataList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
