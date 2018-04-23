package com.abc.controller;

import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.util.MD5;
import com.abc.common.util.RSA;
import com.abc.common.util.SymmetricEncoder;
import com.abc.service.RedisCode;
import com.abc.service.ResponseCookie;
import com.abc.service.RsaV2Service;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.ClientUrlBo;
import com.abc.soa.response.LoginUserBo;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.UserClientBo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by stuy on 2017/8/29.
 */
@Controller
public class ClientController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(ClientController.class);
    /**
     * 财税客户端跳转
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/client_currency.html")
    public ModelAndView clientjump(@RequestParam(value = "data", required = false) String data,
                                   @RequestParam(value = "url", required = false) String url,
                                   HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        if(isNull(data)){
            try {
                long time = System.currentTimeMillis();
                logger.info("调用起始时间:"+time);
                logger.info("请求数据:"+data);
                String []test=data.split(" ");
                RSAPrivateKey privatekey = RedisCacheService.getRedisPrivateKey(request,getInfoService);
                String json= RSA.decryptString(privatekey,data);
                logger.info("JSON数据:"+json);
                ClientUrlBo clientUrlBo = JSON.parseObject(json, ClientUrlBo.class);
                if("csclient".equals(clientUrlBo.getOnly())){
                    if(clientUrlBo.getToken()!=null&&!"".equals(clientUrlBo.getToken())){
                        try {
                            String sessionid=session.getId();
                            UserClientBo baseResponse=RedisCacheService.getRedisUserClient(request,getInfoService,clientUrlBo.getAccessToken(),clientUrlBo.getToken());
                            if("2000".equals(baseResponse.getCode())&&baseResponse.getData()!=null){
                                if(url!=null&&!"".equals(url)){
                                    UserBo userbo = baseResponse.getData();
                                    String userid=userbo.getId();
                                    if(isNull(userbo.getPhone())){
                                        String phones=userbo.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
                                        userbo.setXinphone(phones);
                                    }
                                    Set<byte[]> set = getInfoService.getKeys(userid);
                                    if(set!=null&&set.size()>0){
                                        boolean bool=true;
                                        Map<String,String> map=new HashMap<String,String>();
                                        for(byte [] b :set){
                                            String key=new String(b,"UTF-8");
                                            if(key.indexOf(RedisCode.ONLY)!=-1){
                                                map.put(RedisCode.ONLY,getInfoService.getString(key));
                                            }
                                            if(key.indexOf(RedisCode.USER_ID)!=-1){
                                                map.put(RedisCode.USER_ID,getInfoService.getString(key));
                                            }
                                            if(key.indexOf(RedisCode.USER_TOKEN)!=-1){
                                                String token = getInfoService.getString(key);
                                                if(!token.equals(clientUrlBo.getToken())){
                                                    bool=false;
                                                }
                                                map.put(RedisCode.USER_TOKEN,token);
                                            }
                                            if(map.size()==3){
                                                break;
                                            }
                                        }
                                        if(bool){
                                            setCookieTs(request,response,map,userbo);
                                        }else{
                                            Map<String,String> maps=new HashMap<String,String>();
                                            String only=System.currentTimeMillis()+getFixLenthString(6);
                                            maps.put(RedisCode.ONLY,only);
                                            MD5 md5 = new MD5(userbo.getId());
                                            maps.put(RedisCode.USER_ID,md5.compute());
                                            maps.put(RedisCode.USER_TOKEN,clientUrlBo.getToken());
                                            setCookieTs(request,response,maps,userbo);
                                        }
                                    }else{
                                        Map<String,String> map=new HashMap<String,String>();
                                        String only=System.currentTimeMillis()+getFixLenthString(6);
                                        map.put(RedisCode.ONLY,only);
                                        MD5 md5 = new MD5(userbo.getId());
                                        map.put(RedisCode.USER_ID,md5.compute());
                                        map.put(RedisCode.USER_TOKEN,clientUrlBo.getToken());
                                        setCookieTs(request,response,map,userbo);
                                    }
                                    ModelAndView mav = new ModelAndView("client");
                                    mav.addObject("code","2000");
                                    mav.addObject("url",url);
                                    mav.addObject("messgage","处理成功");
                                    long time1 = System.currentTimeMillis();
                                    logger.info("调用结束时间:"+(time1-time));
                                    return mav;
                                }else{
                                    ModelAndView mav = new ModelAndView("client");
                                    mav.addObject("url",url);
                                    return mav;
                                }
                            }else{
                                ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                                mav.addObject("code","1000");
                                mav.addObject("messgage",baseResponse.getMessage());
                                return mav;
                            }
                        } catch (Exception e) {
                            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                            logger.debug("异常:"+e.getMessage());
                            if ( e instanceof SoaException){
                                mav.addObject("soacode","8888");
                                mav.addObject("message","服务器繁忙,请稍后再试...");
                            }else{
                                mav.addObject("soacode","7777");
                                mav.addObject("message","操作失败,请稍后再试...");
                            }
                            return mav;
                        }
                    }else{
                        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                        mav.addObject("code","1000");
                        mav.addObject("messgage","缺少参数usertoken");
                        return mav;
                    }
                }else{
                    ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                    mav.addObject("code","1000");
                    mav.addObject("messgage","缺少终端必要参数");
                    return mav;
                }
            } catch (SoaException e) {
                ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                logger.debug("异常:"+e.getMessage());
                if ( e instanceof SoaException){
                    mav.addObject("soacode","8888");
                    mav.addObject("message","服务器繁忙,请稍后再试...");
                }else{
                    mav.addObject("soacode","7777");
                    mav.addObject("message","操作失败,请稍后再试...");
                }
                return mav;
            }
        }else{
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
            mav.addObject("code","1000");
            mav.addObject("messgage","缺少Data参数");
            return mav;
        }
    }


    /**
     * 财税客户端跳转
     * @param session
     * @param model
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/client_currency_v2.html")
    public ModelAndView clientjump_v2(@RequestParam(value = "data", required = false) String data,
                                   @RequestParam(value = "url", required = false) String url,
                                   HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        if(isNull(data)){
            try {
                RSAPrivateKey privatekey = RedisCacheService.getRedisV2PrivateKey(request,getInfoService);
                byte[] bs = RsaV2Service.rsaV2Decrypt(data, privatekey);
                String json=new String(bs,"UTF-8");
                logger.info("JSON数据:"+json);
                ClientUrlBo clientUrlBo = JSON.parseObject(json, ClientUrlBo.class);
                if("csclient".equals(clientUrlBo.getOnly())){
                    if(clientUrlBo.getToken()!=null&&!"".equals(clientUrlBo.getToken())){
                        String token="";
                        Map maps=new HashMap();
                        maps.put("userToken",clientUrlBo.getToken());
                        LoginUserBo loginUserBo=SoaConnectionFactory.post(request,ConstantsUri.KEIHUDUAN_LOGIN,maps,LoginUserBo.class);
                        if("2000".equals(loginUserBo.getCode())){
                            //调用Soa用户登录操作
                            setCookie(response,loginUserBo.getData().getUser(),request,loginUserBo.getData().getToken());
                            ModelAndView mav = new ModelAndView("client");
                            mav.addObject("code","2000");
                            mav.addObject("url",url);
                            mav.addObject("messgage","处理成功");
                            return mav;
                        }else{
                            ModelAndView mav= new ModelAndView("soaerror");
                            return mav;
                        }
                    }else{
                        ModelAndView mav= new ModelAndView("soaerror");
                        return mav;
                    }
                }else{
                    ModelAndView mav= new ModelAndView("soaerror");
                    return mav;
                }
            } catch (Exception e) {
                ModelAndView mav= new ModelAndView("soaerror");
                return mav;
            }
        }else{
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    /**
     * 财税客户端存入Cookie
     * @param response
     */
    private void setCookieTs(HttpServletRequest request, HttpServletResponse response,Map<String,String> maps,UserBo userBo){
        String str=System.currentTimeMillis()+getFixLenthString(6);
        Map<String,String> map=new HashMap<String,String>();
        map.put(RedisCode.ONLY,maps.get(RedisCode.ONLY));
        map.put(RedisCode.USER_ID,maps.get(RedisCode.USER_ID));
        map.put(RedisCode.USER_TOKEN,maps.get(RedisCode.USER_TOKEN));
        //map.put("client","abcClient");
        String json=JSON.toJSONString(map);
        String key="";
        try {
            key= URLEncoder.encode(SymmetricEncoder.setMiwen(json),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MD5 md5=new MD5(userBo.getId());
        getInfoService.setClientString(request,map, RedisCode.USER_TOKEN,maps.get(RedisCode.USER_TOKEN));
        getInfoService.setClientString(request,map, RedisCode.ONLY,maps.get(RedisCode.ONLY));
        getInfoService.setClientString(request,map, RedisCode.USER_ID,md5.compute());
        getInfoService.setClientObject(request,map, RedisCode.CURRENT_USER,userBo);

        //带域名和路径的cookie
        ResponseCookie.setCookie(response,"userPicturePath",userBo.getUserPicturePath(),"abc12366.com","/");
        String nickname="";
        try {
            nickname=URLEncoder.encode(userBo.getNickname(),"UTF-8");
            ResponseCookie.setCookie(response,"nickname",nickname,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseCookie.setCookie(response,"key",key,"abc12366.com","/");
        ResponseCookie.setCookie(response,"vipLevel",userBo.getVipLevel(),"abc12366.com","/");

        String vipLevelName="";
        try {
            if(userBo.getVipLevelName()!=null){
                vipLevelName=URLEncoder.encode(userBo.getVipLevelName(),"UTF-8");
            }
            ResponseCookie.setCookie(response,"vipLevelName",vipLevelName,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseCookie.setCookie(response,"level",userBo.getLevel(),"abc12366.com","/");

        String levelName="";
        try {
            if(userBo.getLevelName()!=null){
                levelName=URLEncoder.encode(userBo.getLevelName(),"UTF-8");
            }
            ResponseCookie.setCookie(response,"levelName",levelName,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ResponseCookie.setCookie(response,"client","abcClient","abc12366.com","/");

        //不要路径和域名的cookie
        ResponseCookie.setCookie(response,"userPicturePath",userBo.getUserPicturePath());
        ResponseCookie.setCookie(response,"nickname",nickname);
        ResponseCookie.setCookie(response,"key",key);
        ResponseCookie.setCookie(response,"vipLevel",userBo.getVipLevel());
        ResponseCookie.setCookie(response,"vipLevelName",vipLevelName);
        ResponseCookie.setCookie(response,"level",userBo.getLevel());
        ResponseCookie.setCookie(response,"levelName",levelName);
        ResponseCookie.setCookie(response,"client","abcClient");

        //要路径不要域名的cookie
        ResponseCookie.setCookie(response,"userPicturePath",userBo.getUserPicturePath(),"/");
        ResponseCookie.setCookie(response,"nickname",nickname,"/");
        ResponseCookie.setCookie(response,"key",key,"/");
        ResponseCookie.setCookie(response,"vipLevel",userBo.getVipLevel(),"/");
        ResponseCookie.setCookie(response,"vipLevelName",vipLevelName,"/");
        ResponseCookie.setCookie(response,"level",userBo.getLevel(),"/");
        ResponseCookie.setCookie(response,"levelName",levelName,"/");
        ResponseCookie.setCookie(response,"client","abcClient","/");

    }



    /**
     * 设置cookie
     * @param response
     * @param userBo 用户对象
     */
    private void setCookie(HttpServletResponse response, UserBo userBo, HttpServletRequest request,String token){
        String str=System.currentTimeMillis()+getFixLenthString(6);
        Map<String,String>  map=new HashMap<String,String>();
        //StringBuffer sb=new StringBuffer();
        map.put(RedisCode.USER_TOKEN,token);
        map.put(RedisCode.ONLY,str);
        MD5 md5=new MD5(userBo.getId());
        map.put(RedisCode.USER_ID,md5.compute());
        String json=JSON.toJSONString(map);
        String key="";
        try {
            key=URLEncoder.encode(SymmetricEncoder.setMiwen(json),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        getInfoService.removeKeys(request,userBo.getId());
        getInfoService.setString(request,map,RedisCode.USER_TOKEN,token);
        getInfoService.setString(request,map,RedisCode.ONLY,str);
        getInfoService.setString(request,map,RedisCode.USER_ID,md5.compute());
        getInfoService.setObject(request,map,RedisCode.CURRENT_USER,userBo);

        //带域名和路径的cookie
        ResponseCookie.setCookie(response,"userPicturePath",userBo.getUserPicturePath(),"abc12366.com","/");
        String nickname="";
        try {
            nickname=URLEncoder.encode(userBo.getNickname(),"UTF-8");
            ResponseCookie.setCookie(response,"nickname",nickname,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseCookie.setCookie(response,"key",key,"abc12366.com","/");
        ResponseCookie.setCookie(response,"vipLevel",userBo.getVipLevel(),"abc12366.com","/");

        String vipLevelName="";
        try {
            vipLevelName=URLEncoder.encode(userBo.getVipLevelName(),"UTF-8");
            ResponseCookie.setCookie(response,"vipLevelName",vipLevelName,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String level="";
        if(userBo.getLevel()!=null){
            level=userBo.getLevel();
        }
        ResponseCookie.setCookie(response,"level",level,"abc12366.com","/");

        String levelName="";
        try {
            if(userBo.getLevelName()!=null){
                levelName=URLEncoder.encode(userBo.getLevelName(),"UTF-8");
            }
            ResponseCookie.setCookie(response,"levelName",levelName,"abc12366.com","/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //不要路径和域名的cookie
//        ResponseCookie.setCookie(response,"userPicturePath",userBo.getData().getUser().getUserPicturePath());
//        ResponseCookie.setCookie(response,"nickname",nickname);
//        ResponseCookie.setCookie(response,"key",key);
//        ResponseCookie.setCookie(response,"vipLevel",userBo.getData().getUser().getVipLevel());
//        ResponseCookie.setCookie(response,"vipLevelName",vipLevelName);
//        ResponseCookie.setCookie(response,"level",userBo.getData().getUser().getLevel());
//        ResponseCookie.setCookie(response,"levelName",levelName);

        //要路径不要域名的cookie
        ResponseCookie.setCookie(response,"userPicturePath",userBo.getUserPicturePath(),"/");
        ResponseCookie.setCookie(response,"nickname",nickname,"/");
        ResponseCookie.setCookie(response,"key",key,"/");
        ResponseCookie.setCookie(response,"vipLevel",userBo.getVipLevel(),"/");
        ResponseCookie.setCookie(response,"vipLevelName",vipLevelName,"/");
        ResponseCookie.setCookie(response,"level",userBo.getLevel(),"/");
        ResponseCookie.setCookie(response,"levelName",levelName,"/");

    }

    /**
     * 产生固定长度随机数
     * @param strLength
     * @return
     */
    private static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }



    @GetMapping("/client_token.html")
    public ModelAndView client_token(@RequestParam(value = "data", required = false) String data,
                                     @RequestParam(value = "accessToken", required = false) String accessToken,
                                   HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        if(isNull(data)){
            try {
                getInfoService.setString(RedisCode.TIME_30,RedisCode.ACCESSTOKEN+":"+data,accessToken);
                UserClientBo baseResponse= SoaConnectionFactory.getRestful(request, ConstantsUri.GETUSER_TOKEN,null,UserClientBo.class,data);
                if(baseResponse.getData()!=null){
                    ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                    mav.addObject("code","2000");
                    return mav;
                }else{
                    ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                    mav.addObject("code","-1");
                    mav.addObject("message","userToken超时");
                    return mav;
                }
            } catch (SoaException e) {
                ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
                logger.debug("异常:"+e.getMessage());
                if ( e instanceof SoaException){
                    mav.addObject("soacode","8888");
                    mav.addObject("message","服务器繁忙,请稍后再试...");
                }else{
                    mav.addObject("soacode","7777");
                    mav.addObject("message","操作失败,请稍后再试...");
                }
                return mav;
            }
        }else{
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
            mav.addObject("code","-1");
            mav.addObject("message","缺少data参数");
            return mav;
        }
    }
}
