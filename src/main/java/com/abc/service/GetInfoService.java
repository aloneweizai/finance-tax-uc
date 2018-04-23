package com.abc.service;

import com.abc.common.util.MD5;
import com.abc.common.util.SymmetricEncoder;
import com.abc.soa.response.UserBo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by stuy on 2017/9/25.
 */
@Service
public class GetInfoService {

   @Autowired
   private RedisUserService redisUserService;

    private final static Logger logger = LoggerFactory.getLogger(GetInfoService.class);



    /**
     * 从cookie中获取key
     * @param cookies
     * @return
     */
    private Map<String,String> getCookieKey(Cookie[] cookies){
        Map<String,String> map=new HashMap<String,String>();
        if(cookies!=null&&cookies.length>0) {
            String key = "";
            for (Cookie cookie : cookies) {
                if ("key".equals(cookie.getName())) {
                    key = cookie.getValue();
                }
            }
            if(key!=null&&!"".equals(key)){
                map.put("key",key);
                map.put("status","ok");
            }else{
                map.put("status","error");
            }
        }else{
            map.put("status","error");
        }
        return map;
    }


    /**
     * 从cookie中拿出信息
     * @param request
     * @return
     */
    public Map<String,String> getCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        try{
            Map<String,String> keyMap=getCookieKey(cookies);
            if("ok".equals(keyMap.get("status"))){
                String str= URLDecoder.decode(keyMap.get("key"),"UTF-8");
                String json= SymmetricEncoder.getMiwen(str);
                if(json!=null&&!"".equals(json)){
                    Map<String,String> map=(Map<String,String>) JSON.parse(json);
                    return map;
                }else{
                    logger.debug("key解密失败");
                }
            }else{
                logger.debug("不存在cookies:");
            }
        }catch (Exception e){
            logger.info("出现异常:"+e.getMessage());
        }
        return null;
    }

    /**
     * 生成redis key
     * @param map
     * @param name
     * @return
     */
    public String getRedisKey(Map<String,String> map,String name){
        if(map!=null&&map.size()>0){
            String userid=map.get(RedisCode.USER_ID);
            String only=map.get(RedisCode.ONLY);
            if(userid!=null&&!"".equals(userid)&&only!=null&&!"".equals(only)){
                StringBuffer sb=new StringBuffer();
                StringBuffer key=sb.append(RedisCode.USER_OBJECT).append(":").append(name).append(":").append(userid).append(":").append(only);
                return key.toString();
            }
        }
        return null;
    }

    /**
     * 生成redis key
     * @param map
     * @param name
     * @return
     */
    public String getClientRedisKey(Map<String,String> map,String name){
        if(map!=null&&map.size()>0){
            String userid=map.get(RedisCode.USER_ID);
            String only=map.get(RedisCode.ONLY);
            if(userid!=null&&!"".equals(userid)&&only!=null&&!"".equals(only)){
                StringBuffer sb=new StringBuffer();
                StringBuffer key=sb.append(RedisCode.USER_CLIENT_OBJECT).append(":").append(name).append(":").append(userid).append(":").append(only);
                return key.toString();
            }
        }
        return null;
    }


    public String getString(String name){
        String value=redisUserService.get(name);
        if(value!=null&&!"".equals(value)){
            return value;
        }
        return null;
    }

    public <T extends Object> T getObject(String key, Class<T> class_){
        String value = redisUserService.get(key);
        if(value!=null){
            T t = objectClass(value, class_);
            return t;
        }
        return null;
    }

    public void setObject(HttpServletRequest request,Map<String,String> map,String name,Object object){
        String key=getKey(request,map,name);
        String str = objectJson(object);
        redisUserService.set(key,str, RedisCode.TIME_30);
    }

    public void setClientObject(HttpServletRequest request,Map<String,String> map,String name,Object object){
        String key=getClientRedisKey(map,name);
        String str = objectJson(object);
        if(str!=null&&!"".equals(str)){
            redisUserService.set(key,str, RedisCode.TIME_30);
        }
    }


    public void setObject(long time,String name,Object object){
        String str = JSON.toJSONString(object);
        try {
            String bstr = new BASE64Encoder().encodeBuffer(str.getBytes("UTF-8"));
            redisUserService.set(name,bstr,time);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setString(HttpServletRequest request,Map<String,String> map,String name,String object){
        String key=getKey(request,map,name);
        redisUserService.set(key.getBytes(),object.getBytes(), RedisCode.TIME_30);
    }

    public void setClientString(HttpServletRequest request,Map<String,String> map,String name,String object){
        String key=getClientRedisKey(map,name);
        redisUserService.set(key.getBytes(),object.getBytes(), RedisCode.TIME_30);
    }

    public void setString(long time,String name,String value){
        redisUserService.set(name.getBytes(),value.getBytes(),time);
    }


    public String getString(HttpServletRequest request,String key){
        Map<String, String> map = getCookie(request);
        String keyName=getKey(request,map,key);
        if(keyName!=null){
            return getString(keyName);
        }else{
            return null;
        }
    }


    /**
     * 获取用户信息
     * @param request
     * @return
     */
    public UserBo getUserBo(HttpServletRequest request){
        Map<String, String> map = getCookie(request);
        String key=getKey(request,map, RedisCode.CURRENT_USER);
        if(key!=null&&!"".equals(key)){
            String str = redisUserService.get(key);
            if(str!=null){
                UserBo userBo=objectClass(str,UserBo.class);
                return userBo;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    /**
     * 修改用户信息
     * @param request
     * @return
     */
    public void setUserBo(HttpServletRequest request,Object objects){
        Map<String, String> map = getCookie(request);
        String key=getKey(request,map, RedisCode.CURRENT_USER);
        String str = objectJson(objects);
        redisUserService.set(key,str, RedisCode.TIME_30);
    }

    /**
     * 修改用户信息
     * @param request
     * @return
     */
    public void setString(HttpServletRequest request,String name,String objects){
        Map<String, String> map = getCookie(request);
        String key=getRedisKey(map, name);
        redisUserService.set(key.getBytes(),objects.getBytes(), RedisCode.TIME_30);
    }


    public void removeKeys(HttpServletRequest request,String name){
        MD5 md5=new MD5(name);
        Set<byte[]> set =null;
        if(getClientOrPc(request)){
            set = redisUserService.keys(RedisCode.USER_OBJECT+"*" + md5.compute() + "*");
        }else{
            set = redisUserService.keys(RedisCode.USER_CLIENT_OBJECT+"*" + md5.compute() + "*");
        }
        for(byte[] object : set){
            try {
                String str = new String(object, "UTF-8");
                redisUserService.del(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }


    public void remove(String name){
        redisUserService.del(name);
    }


    public boolean setKeys(String name){
        MD5 md5=new MD5(name);
        Set<byte[]> set = redisUserService.keys(md5.compute() + "*");
        if(set!=null&&set.size()>0){
            return true;
        }
        return false;
    }

    public Set<byte[]> getKeys(String name){
        MD5 md5=new MD5(name);
        Set<byte[]> set = redisUserService.keys(RedisCode.USER_CLIENT_OBJECT+":"+"*"+md5.compute() + "*");
        if(set!=null&&set.size()>0){
            return set;
        }
        return null;
    }


    public void continued(HttpServletRequest request){
        Map<String, String> map = getCookie(request);
        String key=getKey(request,map, RedisCode.CURRENT_USER);
        UserBo userbo = getUserBo(request);
        String str = objectJson(userbo);
        redisUserService.set(key,str, RedisCode.TIME_30);
        key=getKey(request,map, RedisCode.ONLY);
        String value=getString(key);
        redisUserService.set(key.getBytes(),value.getBytes(), RedisCode.TIME_30);
        key=getKey(request,map, RedisCode.USER_ID);
        value=getString(key);
        redisUserService.set(key.getBytes(),value.getBytes(), RedisCode.TIME_30);
        key=getKey(request,map, RedisCode.USER_TOKEN);
        value=getString(key);
        redisUserService.set(key.getBytes(),value.getBytes(), RedisCode.TIME_30);
    }



    private boolean getClientOrPc(HttpServletRequest request){
        Map<String, String> map = getCookie(request);
        if(map!=null&&map.get("client")!=null&&!"".equals(map.get("client"))){
            return false;
        }else{
            return true;
        }
    }


    /**
     * 判断是客户端还是pc浏览器，取不同的key
     * @param request
     * @param map
     * @param key
     * @return
     */
    private String getKey(HttpServletRequest request,Map<String,String> map,String key){
        if(getClientOrPc(request)){
            return getRedisKey(map,key);
        }else{
            return getClientRedisKey(map,key);
        }
    }


    private String objectJson(Object obj){
        if(obj!=null){
            try {
                String json=JSON.toJSONString(obj);
                BASE64Encoder base64 = new BASE64Encoder();
                byte[] str = json.getBytes("UTF-8");
                String jsonBase64 = base64.encodeBuffer(str);
                return jsonBase64;
            } catch (UnsupportedEncodingException e) {
                logger.info("redis Object转json,json然后base64编码");
                logger.info("异常:"+e.getMessage());
            }
            return null;
        }else{
            return null;
        }
    }


    public <T extends Object> T objectClass(String str,Class<T> class_){
        try {
            if(str!=null&&!"".equals(str)){
                String bstr = new String(new BASE64Decoder().decodeBuffer(str),"UTF-8");
                T obj=JSON.parseObject(bstr,class_);
                return obj;
            }
        } catch (IOException e) {
            logger.info("redis json字符串转对象异常");
            logger.info("异常信息:"+e.getMessage());
        }
        return null;
    }

}
