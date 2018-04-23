package com.abc.common.soa;

import com.abc.application.SpringCtxHolder;
import com.abc.common.exception.SoaException;
import com.abc.common.exception.SoaInterfaceException;
import com.abc.common.soa.response.BaseResponse;
import com.abc.service.GetInfoService;
import com.abc.soa.ConstantsUri;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author JiangZuoWei
 * @createTime 2015.11.10 14:54:30
 * @description
 */
@Service
public class SoaConnectionFactory {



	private final static boolean ISDEBUGMOD = false;
	private final static String TESTRETURNJSON = "{\"msg\": \"connect error\", \"code\": \"01\"}";
	private static Logger log = LoggerFactory.getLogger(SoaConnectionFactory.class);

	private static GetInfoService getInfoService=(GetInfoService) SpringCtxHolder.getApplicationContext().getBean("getInfoService");;






	public static <T extends BaseResponse> T postWithoutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, class_, HttpMethod.POST,getInfoService, objects);
		try {
			if (ISDEBUGMOD) {
				res.setDebugMod(ISDEBUGMOD);
				res.setReturnJson(TESTRETURNJSON);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			log.error(e.getMessage());
			//e.printStackTrace();
			throw new SoaException("Soa异常");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T extends BaseResponse> T post(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		saveAccessTokenInSession(request);
		return postWithoutToken(request,uri,obj,class_,objects);
	}


	public static <T extends BaseResponse> T getWithoutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, class_, HttpMethod.GET,getInfoService, objects);
		res.setRestful(false);
		try {
			if (ISDEBUGMOD) {
				res.setDebugMod(ISDEBUGMOD);
				res.setReturnJson(TESTRETURNJSON);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			log.error(e.getMessage());
			//e.printStackTrace();
			throw new SoaException("Soa异常");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T extends BaseResponse> T get(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_) throws SoaException{
		saveAccessTokenInSession(request);
		return getWithoutToken(request, uri, obj, class_);
	}

	public static <T extends BaseResponse> T get(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		saveAccessTokenInSession(request);
		return getWithoutToken(request, uri, obj, class_,objects);
	}

	public static <T extends BaseResponse> T getRestfulWithoutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects)throws SoaException{
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, class_, HttpMethod.GET,getInfoService, objects);
        try {
			if (ISDEBUGMOD) {
				res.setDebugMod(ISDEBUGMOD);
				res.setReturnJson(TESTRETURNJSON);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			log.error(e.getMessage());
			//e.printStackTrace();
			throw new SoaException("Soa异常");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends BaseResponse> T getRestfuloutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		ServletContext application = request.getSession().getServletContext();
		if(StringUtils.isEmpty((String) application.getAttribute("expiresTime")) || StringUtils.isEmpty((String) application.getAttribute("accessToken"))){
			SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
		}else{
			String expiresTime = (String)application.getAttribute("expiresTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isEmpty(expiresTime)){
				SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
			}else{
				Calendar cal = Calendar.getInstance();
				try {
					cal.setTime(sdf.parse((String) application.getAttribute("expiresTime")));
				}catch (Exception e){
					log.error(e.getMessage());
					cal.setTime(new Date());
					e.printStackTrace();
				}
				Calendar currDateTime = Calendar.getInstance();
				currDateTime.setTime(new Date());
				if(currDateTime.after(cal)){
					SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
				}
			}
		}
		return getRestfulWithoutToken(request,uri,obj,class_,objects);
	}

	public static <T extends BaseResponse> T getRestful(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		saveAccessTokenInSession(request);
		return getRestfulWithoutToken(request,uri,obj,class_,objects);
	}
	
	public static <T extends BaseResponse> T put(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, class_, HttpMethod.PUT,getInfoService, objects);
		try {
			if (ISDEBUGMOD) {
				res.setDebugMod(ISDEBUGMOD);
				res.setReturnJson(TESTRETURNJSON);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			log.error(e.getMessage());
			//e.printStackTrace();
			throw new SoaException("Soa异常");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T extends BaseResponse> T delete(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, class_, HttpMethod.DELETE,getInfoService, objects);
		try {
			if (ISDEBUGMOD) {
				res.setDebugMod(ISDEBUGMOD);
				res.setReturnJson(TESTRETURNJSON);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			log.error(e.getMessage());
			//e.printStackTrace();
			throw new SoaException("Soa异常");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T extends BaseResponse> T deleteUnRestful(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, class_, HttpMethod.DELETE,getInfoService, objects);
		res.setRestful(false);
		try {
			if (ISDEBUGMOD) {
				res.setDebugMod(ISDEBUGMOD);
				res.setReturnJson(TESTRETURNJSON);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			log.error(e.getMessage());
//			e.printStackTrace();
			throw new SoaException("Soa异常");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends BaseResponse> T deleteRestfulWithoutToken(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, class_, HttpMethod.DELETE,getInfoService, objects);
		try {
			if (ISDEBUGMOD) {
				res.setDebugMod(ISDEBUGMOD);
				res.setReturnJson(TESTRETURNJSON);
			}
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseObject();
		} catch (SoaInterfaceException e) {
			log.error(e.getMessage());
//			e.printStackTrace();
			throw new SoaException("Soa异常");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends BaseResponse> T deleteRestful(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> class_, Object... objects) throws SoaException{
		saveAccessTokenInSession(request);
		return deleteRestfulWithoutToken(request, uri, obj, class_, objects);
	}
	public static <T extends BaseResponse> List<T> queryList(HttpServletRequest request, ConstantsUri uri, Object obj, Class<T> t)throws SoaException {
		SoaConnection<T> res = new SoaConnection<T>(request, uri, obj, t, HttpMethod.GET,getInfoService);
		try {
			res.sendRequest();
			if(!"".equals(res.parseObject().getCode()) && null!=res.parseObject().getCode()){
				if(continuedAccessToken(request,res.parseObject().getCode())){
					res.sendRequest();
				}
			}
			return res.parseList();
		} catch (SoaInterfaceException e) {
			log.error(e.getMessage());
//			e.printStackTrace();
			throw new SoaException("Soa异常");
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	

	static void checkCode(String str) throws UnsupportedEncodingException {
		System.out.println(str);
		 
        //System.out.println(str.getBytes());
 
        //System.out.println(str.getBytes("GB2312"));
 
       // System.out.println(str.getBytes("ISO8859_1"));
 
        //System.out.println(new String(str.getBytes()));
        
       // System.out.println(new String(str.getBytes(), "GBK"));
        
//        System.out.println(new String(str.getBytes(), "utf-8"));
//
//        System.out.println(new String(str.getBytes(), "GB2312"));
//
//        System.out.println(new String(str.getBytes(), "ISO8859_1"));
 
        //System.out.println(new String(str.getBytes("GB2312")));
 
//        System.out.println(new String(str.getBytes("GB2312"), "GB2312"));
//
//        System.out.println(new String(str.getBytes("GB2312"), "ISO8859_1"));
//
//        System.out.println(new String(str.getBytes("ISO8859_1")));
//
//        System.out.println(new String(str.getBytes("ISO8859_1"), "GB2312"));
//
//        System.out.println(new String(str.getBytes("ISO8859_1"), "ISO8859_1"));
	}

	/**
	 * 设置AccessToken及expiresTime到session中，便于访问soa时设置head相关参数
	 * @param request
	 */
	public static void saveAccessTokenInSession(HttpServletRequest request){
		ServletContext application = request.getSession().getServletContext();
		if(StringUtils.isEmpty((String) application.getAttribute("expiresTime")) || StringUtils.isEmpty((String) application.getAttribute("accessToken"))){
			SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
		}else{
			String expiresTime = (String)application.getAttribute("expiresTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isEmpty(expiresTime)){
				SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
			}else{
				Calendar cal = Calendar.getInstance();
				try {
					cal.setTime(sdf.parse((String) application.getAttribute("expiresTime")));
				}catch (Exception e){
					log.error(e.getMessage());
					cal.setTime(new Date());
					e.printStackTrace();
				}
				Calendar currDateTime = Calendar.getInstance();
				currDateTime.setTime(new Date());
				if(currDateTime.after(cal)){
					SoaAuth.saveAccessTokenInSession(request,ConstantsUri.GET_SOA_ACCESS_TOKEN);
				}
			}
		}
	}

	private static boolean continuedAccessToken(HttpServletRequest request,String returnconde){
		boolean flage=false;
		if("4025".equals(returnconde)||"4035".equals(returnconde)){
			SoaAuth.saveAccessTokenInSession(request, ConstantsUri.GET_SOA_ACCESS_TOKEN);
			flage= true;
		}
		return flage;
	}


}
