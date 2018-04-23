package com.abc.soa.response.region;
import com.abc.common.soa.response.BaseResponse;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class City extends BaseResponse {

	/**市ID**/
	private String cityId;

	/**市名称**/
	private String city;

	/**省ID**/
	private String provinceId;



	public void setCityId(String cityId){
		this.cityId = cityId;
	}

	public String getCityId(){
		return this.cityId;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return this.city;
	}


    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}
