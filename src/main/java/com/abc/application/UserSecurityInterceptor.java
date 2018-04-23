package com.abc.application;

import com.abc.common.soa.SoaConnectionFactory;
import com.abc.service.GetInfoService;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.UncheckUrls;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.UserClientBo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author admin
 * @Date 2017/7/11 16:04
 * 用户登陆拦截器
 */
@Component
public class UserSecurityInterceptor implements HandlerInterceptor{

    private final static Logger logger = LoggerFactory.getLogger(UserSecurityInterceptor.class);




    GetInfoService getInfoService =null;

    Map<String,String> mapKey=null;



    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        getInfoService = (GetInfoService)SpringCtxHolder.getApplicationContext().getBean("getInfoService");
        mapKey= getInfoService.getCookie(httpServletRequest);

        /**
         * 判断用户的Cookie中是否存在only，user_id,user_token
         */
        if(!(mapKey!=null&&mapKey.size()>0&&mapKey.get(RedisCode.ONLY)!=null&&!"".equals(mapKey.get(RedisCode.ONLY))
                &&mapKey.get(RedisCode.USER_ID)!=null&&!"".equals(mapKey.get(RedisCode.USER_ID))
                &&mapKey.get(RedisCode.USER_TOKEN)!=null&&!"".equals(mapKey.get(RedisCode.USER_TOKEN)))){
            retrunHtml(httpServletRequest,httpServletResponse);
            return false;
        }
        if(!isOnly(httpServletRequest)){
            retrunHtml(httpServletRequest,httpServletResponse);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }



    public void retrunHtml(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        try {
            boolean isAjaxRequest=false;
            if(httpServletRequest.getHeader("x-requested-with")!=null && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
                isAjaxRequest = true;
            }
            PrintWriter out = httpServletResponse.getWriter();
            StringBuffer sb=new StringBuffer();
            if(isAjaxRequest){
                Map<String,String> map=new HashMap<String,String>();
                map.put("errorCode","0000");
                map.put("js","if(typeof window.top.loc !=='undefined'){ top.loc.GoHome();}else{window.parent.layer.open({type:1,title: false,area: '480px',id:'12345678',zIndex:9999,content:parent.$('#login_layer'),closeBtn: 0,btnAlign: 'c' });}");
                String json=JSON.toJSONString(map);
                sb.append(json);
            }else{
                String message="您登录超时，请重新登录系统！";
                String coderMessage=URLEncoder.encode(message,"UTF-8");
                sb.append("<script>");
                sb.append("if(typeof window.top.loc !=='undefined'){top.loc.GoHome();}else{");
                sb.append("var message=decodeURI(decodeURI('").append(coderMessage).append("'));");
                //sb.append("alert(message);");

                sb.append("window.parent.location.href='").append(httpServletRequest.getContextPath()).append("/login").append("'");
                sb.append("}");
                sb.append("</script>");
            }
            out.print(sb.toString());
            out.close();
        } catch (IOException e) {
            logger.debug("异常错误:"+e.getMessage());
        }
    }

    /**
     * 判断是否是同一个客户端ONLY是否相同
     * @param httpServletRequest
     * @return
     */
    private boolean isOnly(HttpServletRequest httpServletRequest){
        boolean bool=true;
        Object objonly = getInfoService.getString(httpServletRequest,RedisCode.ONLY);
        Object objuserid = getInfoService.getString(httpServletRequest,RedisCode.USER_ID);
        Object objuserToken = getInfoService.getString(httpServletRequest,RedisCode.USER_TOKEN);
        try{
            if(mapKey.get(RedisCode.ONLY)==null||mapKey.get(RedisCode.USER_TOKEN)==null){
                if(objonly!=null&&!"".equals(objonly)&&objuserid!=null&&!"".equals(objuserid)&&objuserToken!=null&&!"".equals(objuserToken)){
                    bool=IfUserTokenOrOnly(objonly.toString(),objuserid.toString(),objuserToken.toString());
                }else{
                    bool=false;
                }
            }else{
                bool=IfUserTokenOrOnly(objonly.toString(),objuserid.toString(),objuserToken.toString());
            }
        }catch (Exception e){
            logger.info("出现异常:"+e.getMessage());
            bool=false;
        }
        return bool;
    }


    /**
     * 判断userToken和only是否一致
     * @return
     */
    private Boolean IfUserTokenOrOnly(String only,String userid,String usertoken){
        if(mapKey.get(RedisCode.ONLY).equals(only)){
            if(mapKey.get(RedisCode.USER_TOKEN).equals(usertoken.toString())){
                if(mapKey.get(RedisCode.USER_ID).equals(userid)){
                    return true;
                }else{
                    logger.debug("Userid匹配失败");
                    return false;
                }
            }else{
                logger.debug("userToken匹配失败");
                return false;
            }
        }else{
            logger.debug("only匹配错误:"+only.toString());
            return false;
        }
    }

}
