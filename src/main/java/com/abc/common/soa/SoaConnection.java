package com.abc.common.soa;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.SoaInterfaceException;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.HttpClientUtils;
import com.abc.common.util.NetWorkUtil;
import com.abc.service.GetInfoService;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.common.util.CommonUtils;
import com.abc.common.util.ReflectUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月10日 下午3:54:30
 * @description
 */
public class SoaConnection<T> extends Connection<T> {

	public final static ObjectMapper MAPPER = new ObjectMapper();
	private static RestTemplate restTemplate;



	static {
		int readTimeout =30000;
		int connectTimeout=10000;
//		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//		requestFactory.setReadTimeout(readTimeout);
//		requestFactory.setConnectTimeout(connectTimeout);
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		clientHttpRequestFactory.setConnectTimeout(connectTimeout);
		clientHttpRequestFactory.setReadTimeout(readTimeout);
		restTemplate = new RestTemplate(clientHttpRequestFactory);
//		restTemplate = new RestTemplate();
//		restTemplate.setRequestFactory(requestFactory);
	}

	protected Class<T> class_;
	private Object parameters;
	private boolean isDebugMod;
	private HttpMethod httpMethod;
	private HttpServletRequest request;
	private boolean isRestful = true;
	private Object[] objects;

	private GetInfoService getInfoService;

	SoaConnection(HttpServletRequest request, ConstantsUri requestUri, Object parameters,
				  Class<T> t, HttpMethod httpMethod,GetInfoService getInfoService, Object... objects) {
		super(requestUri, httpMethod.name());
		this.request = request;
		this.class_ = t;
		this.parameters=parameters;
		this.httpMethod = httpMethod;
		this.objects = objects;
		this.getInfoService=getInfoService;
		//设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//禁止使用int代表Enum的order()來反序列化Enum,非常危險
		MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
	}

	SoaConnection(HttpServletRequest request, ConstantsUri requestUri, Object parameters,
				  Class<T> t, HttpMethod httpMethod, Object... objects) {
		super(requestUri, httpMethod.name());
		this.request = request;
		this.class_ = t;
		this.parameters=parameters;
		this.httpMethod = httpMethod;
		this.objects = objects;
		//this.getInfoService=getInfoService;
		//设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//禁止使用int代表Enum的order()來反序列化Enum,非常危險
		MAPPER.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
	}

	static String checkCode(String str,String url) throws UnsupportedEncodingException {
		/*if(!url.endsWith("08001")){  //纳税人文书查询
			return new String(str.getBytes("ISO8859_1"), "utf-8");
		}
		return new String(str.getBytes("ISO8859_1"), "utf-8").toLowerCase();*/
		return str;
	}

	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

	@Override
	T parseObject() {
		try {
			T res = MAPPER.readValue(this.getReturnJson(), class_);
			return res;
		} catch (Exception e) {
			log.error("SoaConnection.parseObject()  soa返回json格式异常", e);
			e.printStackTrace();
			return (T) new BaseResponse("-999", "SOA返回数据格式异常，请联系管理员");
		}
	}

	@Override
	List<T> parseList() {
		try {
            JavaType javaType = getCollectionType(ArrayList.class, class_);
            List<T> res =  (List<T>)MAPPER.readValue(this.getReturnJson(), javaType);
            return res;
        } catch (Exception e) {
			String msg = "SoaConnection类中json转List异常";
			log.error(msg, e);
            e.printStackTrace();
        }
		return null;
	}

	@Override
	void connect() throws SoaInterfaceException {
		long time = System.currentTimeMillis();
		String userToken = null;
		try {
			if (this.isDebugMod) {
				throw new SoaInterfaceException("debug mod");
			}
			HttpHeaders headers = new HttpHeaders();
			headers.add("Version", SpringCtxHolder.getProperty("abc.version"));

			if (this.request != null) {
				if(this.getInfoService!=null){
					Map<String, String> map = this.getInfoService.getCookie(this.request);
					if(map!=null&&map.size()>0){
						String usertoken=this.getInfoService.getRedisKey(map, RedisCode.USER_TOKEN);
						String user_token=this.getInfoService.getString(usertoken);
						if(user_token!=null&&!"".equals(user_token)){
							headers.add("User-token", user_token);
						}else{
							HttpSession session = request.getSession();
							user_token=RedisCode.USER_TOKEN+":"+session.getId();
							usertoken=this.getInfoService.getString(user_token);
							if(usertoken!=null&&!"".equals(usertoken)){
								headers.add("User-token", usertoken);
							}
						}
					}else{
						HttpSession session = request.getSession();
						String user_token=RedisCode.USER_TOKEN+":"+session.getId();
						String usertoken=this.getInfoService.getString(user_token);
						if(usertoken!=null&&!"".equals(usertoken)){
							headers.add("User-token", usertoken);
						}
					}
				}

				ServletContext application = this.request.getSession().getServletContext();
				if (application.getAttribute("accessToken") != null) {
					String accessToken = (String) application.getAttribute("accessToken");
					headers.add("Access-Token", accessToken);
				}


				headers.add("Client-Ip", NetWorkUtil.getClientIp(this.request));
				headers.add("Client-User-Agent", this.request.getHeader("User-Agent"));
			}

			log.info("请求Uri:" + this.getRemoteUrl());
			log.info("请求参数:" + MAPPER.writeValueAsString(this.parameters));
			HttpEntity<String> json =restTemplate.exchange(this.getRemoteUrl(), this.httpMethod, new HttpEntity<Object>(this.parameters, headers), String.class, objects);

			time = System.currentTimeMillis() - time;
			log.info(this.getConstantsUri().describe + " <" + this.getRemoteUrl() + ">连接时间: " + time + " ms");
			ResponseEntity<String> rs = (ResponseEntity<String>)json;

			log.info(this.getConstantsUri().describe + "返回StatusCode:"+ rs.getStatusCodeValue() +",json body内容：" + json.getBody());
			if(CommonUtils.nullOrBlank(json.getBody())){
				this.setReturnJson("{\"success\": false,\"code\":\"-999\",\"msg\":\"SOA 返回数据异常，请稍后再试！\"}");
			}else{
				this.setReturnJson(json.getBody());
			}
		} catch(Exception e) {
			String erroInfo = "调用" + this.getConstantsUri().describe  + "异常 <url=" + this.getRemoteUrl() + ">";
			log.error(erroInfo, e);
			//this.setReturnJson("{\"success\": false,\"code\":\"-999\",\"msg\":\"SOA 返回数据异常，请稍后再试！\"}");
			throw new SoaInterfaceException("soa异常");
		}
	}

	@Override
	void createUrl() {
		this.setHost(SpringCtxHolder.getProperty("abc.soa-url"));
		String uri = this.getHost() + this.getConstantsUri().uri;
		if (!this.isRestful && this.parameters != null) {
			StringBuffer url = new StringBuffer();
			url.append(!this.isRestful ? uri + "?" : uri);

			Map<String, String> map = null;
			try {
				if (!(this.parameters instanceof Map)) {
					map = ReflectUtils.getObjFieldValue(this.parameters);
				} else {
					map = (Map<String, String>) this.parameters;
				}
				if(map != null) {
					Set<String> keys = map.keySet();
					if(keys != null){
						for (String key : keys) {
							String val = (String) map.get(key);
                            url.append("&" + key + "=" + val);
						}
					}
				}
				uri = url.toString();
				this.parameters = null;
			} catch (Exception e){
				String msg = "组装" + this.getConstantsUri().describe + "请求参数异常!";
				log.error(msg, e);
				throw new SoaInterfaceException(msg);
			}
		}
		System.out.println(uri);
		this.setRemoteUrl(uri);
	}

	public boolean isDebugMod() {
		return isDebugMod;
	}

	public void setDebugMod(boolean isDebugMod) {
		this.isDebugMod = isDebugMod;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public boolean isRestful() {
		return isRestful;
	}

	public void setRestful(boolean isRestful) {
		this.isRestful = isRestful;
	}



}
