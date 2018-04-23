package com.abc.common.soa;

import com.abc.common.exception.SoaInterfaceException;
import com.abc.common.util.BaseObject;
import com.abc.soa.ConstantsUri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月10日 下午3:54:30
 * @description
 */
public abstract class Connection<T> extends BaseObject {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private String host;

	private String formType = "POST";

	private String codeType = "UTF-8";

	private String returnJson;

	private String remoteUrl;
	
	private ConstantsUri constantsUri;
	
	private int timeout;
	
	Connection(ConstantsUri requestUri, String formType) {
		this.setConstantsUri(requestUri);
		this.formType = formType;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getReturnJson() {
		return returnJson;
	}

	public void setReturnJson(String returnJson) {
		this.returnJson = returnJson;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public ConstantsUri getConstantsUri() {
		return constantsUri;
	}

	public void setConstantsUri(ConstantsUri constantsUri) {
		this.constantsUri = constantsUri;
	}
	
	public void sendRequest() throws SoaInterfaceException {
		this.createUrl();
		this.connect();
	}
	
	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	abstract void createUrl();

	abstract void connect () throws SoaInterfaceException;

	abstract T parseObject();

	abstract List<T> parseList();

}
