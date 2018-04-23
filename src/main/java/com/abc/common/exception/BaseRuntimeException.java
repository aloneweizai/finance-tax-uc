package com.abc.common.exception;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月10日 下午3:19:44
 * @description
 */
public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;
	
	private String message;
	
	public BaseRuntimeException(String message) {
		super(message);
		this.message = message;
	}
	
	public BaseRuntimeException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BaseRuntimeException(String message, Throwable e) {
		super(message, e);
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
