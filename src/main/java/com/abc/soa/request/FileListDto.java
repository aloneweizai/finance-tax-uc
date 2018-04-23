package com.abc.soa.request;

import com.abc.common.soa.response.BaseResponse;

import java.util.List;

/**
 * Created by relic5 on 2017/6/11.
 */
public class FileListDto extends BaseResponse {

    private List<FileDto> dataList;

    public List<FileDto> getDataList() {
        return dataList;
    }

    public void setDataList(List<FileDto> dataList) {
        this.dataList = dataList;
    }
}
