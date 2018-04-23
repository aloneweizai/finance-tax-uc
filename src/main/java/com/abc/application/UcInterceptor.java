package com.abc.application;

import com.abc.service.GetInfoService;
import com.abc.soa.response.UserBo;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by stuy on 2017/9/30.
 */
@Aspect
@Component
public class UcInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(UcInterceptor.class);

    @Autowired
    private GetInfoService getInfoService;
    //controller包的子包里面任何方法
    @Pointcut("execution(public org.springframework.web.servlet.ModelAndView com.abc.controller.*.*.*(..))")
    public void checkToken(){
    }


    @Pointcut("execution(public org.springframework.web.servlet.ModelAndView com.abc.controller.*.*(..))")
    public void checkToken_(){

    }
    @Before("checkToken()")
    public void before(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        UserBo userbo = getInfoService.getUserBo(request);
        if(userbo!=null){
            getInfoService.continued(request);
        }
    }


    @Before("checkToken_()")
    public void before_(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        UserBo userbo = getInfoService.getUserBo(request);
        if(userbo!=null){
            getInfoService.continued(request);
        }
    }




    @AfterReturning(pointcut="checkToken()",returning="mav")
    public void afterCheckToken(ModelAndView mav){
        String ucdomain= SpringCtxHolder.getProperty("ucdomain");
        String snsdomain= SpringCtxHolder.getProperty("snsdomain");
        String cswdomain= SpringCtxHolder.getProperty("cswdomain");
        mav.addObject("ucurl",ucdomain);
        mav.addObject("snsurl",snsdomain);
        mav.addObject("cswurl",cswdomain);
        String imagedomain= SpringCtxHolder.getProperty("imagedomain");
        mav.addObject("picurl",imagedomain);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserBo user = getInfoService.getUserBo(request);
        if(user!=null){
            mav.addObject("currentUser",user);
        }
        //System.out.println(user.getId());
    }

    @AfterReturning(pointcut="checkToken_()",returning="mav")
    public void afterCheckToken_(ModelAndView mav){
        String ucdomain= SpringCtxHolder.getProperty("ucdomain");
        String snsdomain= SpringCtxHolder.getProperty("snsdomain");
        String cswdomain= SpringCtxHolder.getProperty("cswdomain");
        mav.addObject("ucurl",ucdomain);
        mav.addObject("snsurl",snsdomain);
        mav.addObject("cswurl",cswdomain);
        String imagedomain= SpringCtxHolder.getProperty("imagedomain");
        mav.addObject("picurl",imagedomain);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserBo user = getInfoService.getUserBo(request);
        if(user!=null){
            mav.addObject("currentUser",user);
        }
        //System.out.println(user.getId());
    }

}
