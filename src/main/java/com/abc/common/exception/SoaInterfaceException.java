package com.abc.common.exception;
/**
 * @author admin
 * @createTime 2015年11月10日 下午3:19:11
 * @description 
 */
public class SoaInterfaceException extends BaseRuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SoaInterfaceException(String code, String message) {
		super(code, message);
	}

	public SoaInterfaceException(String message) {
		super(message);
	}
	
}
