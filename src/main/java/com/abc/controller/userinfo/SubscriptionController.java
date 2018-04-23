package com.abc.controller.userinfo;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.Subscriptions;
import com.abc.bean.userinfo.UserSubscription;
import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.userinfo.SubscriptionReq;
import com.abc.soa.response.DictRes;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.userinfo.SubscriptionResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 消息订阅
 * Created by andy on 2018/3/5.
 */
@Controller
@RequestMapping(value = "/subscription")
public class SubscriptionController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    /**
     * 订阅列表
     * @param request
     * @param type
     * @param busiType
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/subscription.html")
    public ModelAndView selectList(HttpServletRequest request,
                                     @RequestParam(value = "type",required = false,defaultValue = "") String type,
                                     @RequestParam(value = "busiType",required = false,defaultValue = "") String busiType,
                                     @RequestParam(value = "page",required = false,defaultValue = "1")Integer page,
                                     @RequestParam(value = "size",required = false,defaultValue = "20")Integer size) {
        ModelAndView mav = new ModelAndView("userinfo/subscription");
        try {
            SubscriptionReq subscriptionReq = new SubscriptionReq();
            subscriptionReq.setType(type);
            subscriptionReq.setBusiType(busiType);
            subscriptionReq.setPage(page);
            subscriptionReq.setSize(size);
            SubscriptionResp subscriptionResp = SoaConnectionFactory.get(request, ConstantsUri.MSG_SUBSCRIPTIONS_SETTING, subscriptionReq, SubscriptionResp.class);
            List<Subscriptions> subList =subscriptionResp.getDataList();
            Map<String,List<Subscriptions>> maps = new HashMap<>();
            if(subList !=null && subList.size()>0){
                Set<String>msgType = new HashSet<>();
                for(Subscriptions subscriptions :subList){
                    msgType.add(subscriptions.getType());
                }
                if(msgType.size()>0){
                    for(String mType : msgType){
                        List<Subscriptions>subs = new ArrayList<>();
                        for(Subscriptions subscriptions :subList) {
                            if(mType.equals(subscriptions.getType())){
                                subs.add(subscriptions);
                            }
                        }
                        maps.put(mType,subs);
                    }
                }
            }
            mav.addObject("subes",maps);
            DictRes msgTypes = RedisCacheService.getRedisDictRes(request, getInfoService, "msgType");
            mav.addObject("msgTypes",msgTypes.getDataList());
            DictRes busiTypes = RedisCacheService.getRedisDictRes(request, getInfoService, "busiType");
            mav.addObject("busiTypes",busiTypes.getDataList());
        }catch (SoaException e){
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
     * 订阅确定
     * @param request
     * @return
     */
    @RequestMapping(value = "/setSub",method = RequestMethod.POST)
    public ModelAndView setSubscription(HttpServletRequest request,@RequestBody List<UserSubscription> dataList){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try{
            UserBo userBo = getInfoService.getUserBo(request);
            if(dataList!=null && dataList.size()>0 && userBo!=null){
                for(UserSubscription us : dataList){
                    us.setUserId(userBo.getId());
                }
            }
            BaseResponse baseResponse = SoaConnectionFactory.post(request, ConstantsUri.MSG_SUBSCRIPTIONS_SAVE, dataList, BaseResponse.class);
            mav.addObject("data",baseResponse);
        }catch (SoaException e){
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
        return  mav;
    }


    /**
     * 初始化(重置)
     * @param request
     * @return
     */
    @RequestMapping(value = "/init",method = RequestMethod.POST)
    public ModelAndView initSubscription(HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try{
            BaseResponse baseResponse = SoaConnectionFactory.post(request, ConstantsUri.MSG_SUBSCRIPTIONS_INIT, null, BaseResponse.class);
            mav.addObject("data",baseResponse);
        }catch (SoaException e){
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
        return  mav;
    }
}
