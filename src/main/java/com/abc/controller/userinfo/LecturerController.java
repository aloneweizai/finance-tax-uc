package com.abc.controller.userinfo;

import com.abc.bean.userinfo.FollowLecturerBO;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.response.userinfo.LecturerListResp;
import com.abc.soa.response.userinfo.LeturerResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by andy on 2018/3/13.
 */
@Controller
@RequestMapping(value = "/lecturer")
public class LecturerController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(LecturerController.class);

    /**
     * 关注讲师
     */
    @RequestMapping(value = "/attention",method = RequestMethod.POST)
    public ModelAndView attention(HttpServletRequest request,String id){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            BaseResponse baseResponse = SoaConnectionFactory.put(request, ConstantsUri.LECTURER_FL_UPDATE, null, BaseResponse.class,id);
            mav.addObject("data", baseResponse);
        } catch (SoaException e){
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
     * 取消关注
     */
    @RequestMapping(value = "/unsubscribe",method = RequestMethod.POST)
    public ModelAndView unsubscribe(HttpServletRequest request,@RequestParam(value = "id") String id){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            if(isNull(id)){
                BaseResponse baseResponse = SoaConnectionFactory.put(request, ConstantsUri.LECTURER_FL_UPDATE, null, BaseResponse.class, id);
                mav.addObject("data", baseResponse);
            }
        } catch (SoaException e){
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
