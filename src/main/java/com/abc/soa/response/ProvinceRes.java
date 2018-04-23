package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;

/**
 * Created by stuy on 2017/7/20.
 */
public class ProvinceRes extends BaseResponse {
    private ProvinceBo provinceList;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ProvinceBo getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(ProvinceBo provinceList) {
        this.provinceList = provinceList;
    }
}
