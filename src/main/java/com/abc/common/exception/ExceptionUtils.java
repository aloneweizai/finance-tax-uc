package com.abc.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;

/**
 * Created by admin on 5/5/15.
 */
public class ExceptionUtils {

    private static Logger log = LoggerFactory.getLogger(ExceptionUtils.class);
    private static Map<String, PropertyResourceBundle> propertyResourceBundleMap;
    static {
        propertyResourceBundleMap = new HashMap<String, PropertyResourceBundle>();
    }

    public static String getMessage(String errorCode, Object... params) {

        String filename = "message.properties";
        if (!propertyResourceBundleMap.containsKey(filename)) {
            Resource resource = new ClassPathResource(filename);
            try {
                propertyResourceBundleMap.put(filename, new PropertyResourceBundle(resource.getInputStream()));
            } catch (IOException ignored) {
                log.error(ignored.getMessage());
            }
        }
        String message;
        try {
            message = propertyResourceBundleMap.get(filename).getString(errorCode);
        } catch (Exception e) {
            log.error(e.getMessage());
            message = "其他";
        }
        int index = 1;
        if(params != null){
            for (Object obj : params) {
                message = message.replaceAll("%" + index++ + "%", String.valueOf(obj));
            }
        }

        return message;
    }

    public static ModelAndView processReturn(ModelAndView mav, String rescode, String tokenid) {
        mav.addObject("rescode", rescode);
        mav.addObject("message", getMessage(rescode));
        mav.addObject("tokenid", tokenid);
        return mav;
    }

    public static ModelAndView processReturn(ModelAndView mav, String rescode) {
        mav.addObject("rescode", rescode);
        mav.addObject("message", getMessage(rescode));
        return mav;
    }

    /**
     * 处理返回信息
     * @param yhm 用于记录用户访问日志的用户名
     * @param mav
     * @param rescode
     * @return
     */
    public static ModelAndView processReturn(String yhm, ModelAndView mav, String rescode) {
        mav.addObject("rescode", rescode);
        mav.addObject("message", getMessage(rescode));
        mav.addObject("yhm", yhm);
        return mav;
    }
}
