package com.abc.common.soa.response;

import com.abc.common.util.CommonUtils;

/**
 * @author admin
 * @createTime 2015年11月10日 下午3:52:30
 * @description 
 */
public class BaseResponse implements java.io.Serializable{


	private boolean success = false;
	
	private String code;
	
	private String message;

	protected BaseResponse(){}

	public BaseResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	
	public boolean isSuccess() {
		if (!CommonUtils.nullOrBlank(code) && "2000".equals(code)) {
			this.success = true;
		}
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
