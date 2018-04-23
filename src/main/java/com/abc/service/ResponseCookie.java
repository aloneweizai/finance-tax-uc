package com.abc.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by stuy on 2018/1/10.
 */
public class ResponseCookie {

    public static void setCookie(HttpServletResponse response,String key, String value){
        Cookie cookie=new Cookie(key,value);
        response.addCookie(cookie);
    }

    public static void setCookie(HttpServletResponse response,String key, String value,String domain,String path){
        Cookie cookie=new Cookie(key,value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        response.addCookie(cookie);
    }

    public static void setCookie(HttpServletResponse response,String key, String value,String path){
        Cookie cookie=new Cookie(key,value);
        cookie.setPath(path);
        response.addCookie(cookie);
    }
}
