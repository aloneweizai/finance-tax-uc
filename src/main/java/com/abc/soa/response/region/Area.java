package com.abc.soa.response.region;
import com.abc.common.soa.response.BaseResponse;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Area extends BaseResponse {

	/**地区ID**/
	private String areaId;

	/**地区名称**/
	private String area;

	/**市ID**/
	private String cityId;



	public void setAreaId(String areaId){
		this.areaId = areaId;
	}

	public String getAreaId(){
		return this.areaId;
	}

	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return this.area;
	}

	public void setCityId(String cityId){
		this.cityId = cityId;
	}

	public String getCityId(){
		return this.cityId;
	}

}
