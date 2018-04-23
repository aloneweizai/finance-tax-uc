package com.abc.controller;

import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.LoginReq;
import com.abc.soa.request.SmsReq;
import com.abc.soa.request.SmsRequ;
import com.abc.soa.response.RegisterBo;
import com.abc.soa.response.SmsBo;
import com.abc.soa.response.UserBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by stuy on 2017/7/18.
 */
@Controller
public class SendCodeController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(SendCodeController.class);
    /**
     * 发送短信通用请求
     * @param phone
     * @param type
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/sms.html", method = RequestMethod.POST)
    public ModelAndView batchDel(@RequestParam(value = "phone", required = false) String phone,
                                 @RequestParam(value = "type", required = false) String type,HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(phone)&&isNull(type)){
                if(phone.indexOf("****")>-1){
                    UserBo userBo = getInfoService.getUserBo(request);
                    phone=userBo.getPhone();
                }
                SmsRequ smsRequ=new SmsRequ();
                smsRequ.setPhone(phone);
                smsRequ.setType(type);
                SmsBo smsBo=SoaConnectionFactory.post(request,ConstantsUri.SMS_CODE,smsRequ, SmsBo.class);
                mav.addObject("result", smsBo);
            }
        } catch (Exception e) {
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
        return mav;
    }


    /**
     * 发送短信通用请求
     * @param phone
     * @param type
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/phonelongsms.html", method = RequestMethod.POST)
    public ModelAndView phonelongsms(@RequestParam(value = "phone", required = false) String phone,
                                 @RequestParam(value = "type", required = false) String type,HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(phone)&&isNull(type)){
                if(phone.indexOf("****")>-1){
                    UserBo userBo = getInfoService.getUserBo(request);
                    phone=userBo.getPhone();
                }
                SmsRequ smsRequ=new SmsRequ();
                smsRequ.setPhone(phone);
                smsRequ.setType(type);
                SmsBo smsBo=SoaConnectionFactory.post(request,ConstantsUri.SMS_COD_PHONELOGIN,smsRequ, SmsBo.class);
                mav.addObject("result", smsBo);
            }
        } catch (Exception e) {
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
        return mav;
    }





    /**
     * 发送短信通用请求
     * @param type
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/xinsms.html", method = RequestMethod.POST)
    public ModelAndView xinsms(@RequestParam(value = "type", required = false) String type,HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            if(isNull(type)){
                UserBo userBo=getInfoService.getUserBo(request);
                SmsRequ smsRequ=new SmsRequ();
                smsRequ.setType(type);
                smsRequ.setUserId(userBo.getId());
                SmsBo smsBo=SoaConnectionFactory.post(request,ConstantsUri.SMS_CODE_NOTPHONE,smsRequ, SmsBo.class);
                mav.addObject("result", smsBo);
            }
        } catch (Exception e) {
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
        return mav;
    }
}
