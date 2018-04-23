package com.abc.soa.response;

import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.response.region.Area;
import com.abc.soa.response.region.City;

/**
 * Created by stuy on 2017/8/9.
 */
public class DizhiNameBo extends BaseResponse {

    private ProvinceBo province;

    private City city;

    private Area area;

    public ProvinceBo getProvince() {
        return province;
    }

    public void setProvince(ProvinceBo province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
