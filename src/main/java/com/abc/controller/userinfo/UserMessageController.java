package com.abc.controller.userinfo;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.BusinessMessage;
import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.userinfo.MessageReq;
import com.abc.soa.response.DictRes;
import com.abc.soa.response.userinfo.BusinessMessageResp;
import com.abc.soa.response.userinfo.MessageBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author tankh
 * @createTime 2017年7月18日 
 * @description 我的消息Controller
 */
@Controller
@RequestMapping(value = "/userinfo")
public class UserMessageController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(UserMessageController.class);

    @GetMapping("/user_message.html")
    public String getMessage(@RequestParam(value = "index", required = false,defaultValue = "1") String index,
                             @RequestParam(value = "type",required = false,defaultValue = "4")String type,
                             @RequestParam(value = "size",required = false,defaultValue = "10")Integer size,
                             HttpServletRequest request,Model model,HttpSession session) {
        try {
            MessageReq messageReq = new MessageReq();
            if(isNull(type) && type.equals("4")) {//未读消息
                messageReq.setStatus("1");
            }else{
                messageReq.setType(type);
                messageReq.setStatus("2");
            }
            messageReq.setPage(Integer.valueOf(index));
            messageReq.setSize(size);
            messageReq.setBusiType(null);
            BusinessMessageResp messageResp = SoaConnectionFactory.get(request, ConstantsUri.UC_MESSAGES, messageReq,BusinessMessageResp.class);
            List<BusinessMessage> messages = messageResp.getDataList();
            model.addAttribute("messages",messages);
            if(isNull(type) && type.equals("1")) {
                int sysTotal = 0;
                if (messages != null && messages.size() > 0) {
                    for (BusinessMessage msg : messages) {
                        if ("1".equals(msg.getStatus())) {
                            sysTotal++;
                        }
                    }
                }
                model.addAttribute("sysTotal", sysTotal);
            }else if(isNull(type) && type.equals("2")){
                int bbTotal = 0;
                if(messages != null && messages.size() >0){
                    for (BusinessMessage msg:messages){
                        if("1".equals(msg.getStatus())){
                            bbTotal++;
                        }
                    }
                }
                model.addAttribute("bbTotal", bbTotal);
            }
            if(messageResp.getTotal()>0){
                model.addAttribute("page",index);
                model.addAttribute("count", messageResp.getTotal());
            }else{
                model.addAttribute("page",0);
                model.addAttribute("count",0);
            }
            model.addAttribute("type", type);
            DictRes msgTypes = RedisCacheService.getRedisDictRes(request, getInfoService, "msgType");
            model.addAttribute("msgTypes", msgTypes.getDataList());
            DictRes busiTypes = RedisCacheService.getRedisDictRes(request, getInfoService, "busiType");
            model.addAttribute("busiTypes", busiTypes.getDataList());
            return "userinfo/user_message";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }

    /**
     * 标记消息为已读
     * @param request
     * @return
     */
    @RequestMapping(value = "/bsMessage/{id}",method = RequestMethod.POST)
    public ModelAndView readBusiness(@PathVariable(value = "id")String id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            MessageBO messageBO = SoaConnectionFactory.put(request, ConstantsUri.UC_MESSAGES_ID, null, MessageBO.class, id);
            mav.addObject("data", messageBO);
        } catch (SoaException e) {
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
     * 分页标记已读
     * @param request
     * @return
     */
    @RequestMapping(value = "/message/readPage",method = RequestMethod.POST)
    public ModelAndView readPage(HttpServletRequest request, String[] msgId, String type){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            Map<String, Object> parameter = new HashMap<>();
            if(msgId != null) {
                parameter.put("ids", Arrays.asList(msgId));
            }
            BaseResponse baseResp = SoaConnectionFactory.put(request, ConstantsUri.UC_MESSAGES_BITCH, parameter, MessageBO.class);
            mav.addObject("data", baseResp);
        } catch (SoaException e) {
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
     * 全部已读
     * @param request
     * @return
     */
    @RequestMapping(value = "/message/readAll",method = RequestMethod.POST)
    public ModelAndView readAll(@RequestParam(value = "type")String type,HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            MessageReq messageReq = new MessageReq();
            if(isNull(type) && type.equals("4")) {//未读消息
                messageReq.setStatus("1");
            }else{
                messageReq.setType(type);
                messageReq.setStatus("2");
            }
            messageReq.setPage(0);
            messageReq.setSize(0);
            messageReq.setBusiType(null);
            BusinessMessageResp messageResp = SoaConnectionFactory.get(request, ConstantsUri.UC_MESSAGES, messageReq,BusinessMessageResp.class);
            List<BusinessMessage> messages = messageResp.getDataList();
            BaseResponse baseResp = null;
            if(messages != null && messages.size()>0) {
                for(BusinessMessage msg : messages){
                    baseResp = SoaConnectionFactory.put(request, ConstantsUri.UC_MESSAGES_ID, null, MessageBO.class, msg.getId());
                }
            }
            mav.addObject("data", baseResp);
        } catch (SoaException e) {
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
